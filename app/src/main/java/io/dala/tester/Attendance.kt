package io.dala.tester

import android.R.attr.title
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.data.position
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import kotlin.collections.addAll
import kotlin.collections.first

data class PublicHoliday(val date: LocalDate, val name: String)

fun getPublicHolidays(yearMonth: YearMonth): List<PublicHoliday> {
    val holidays = mutableListOf<PublicHoliday>()
    if (yearMonth.year == LocalDate.now().year) { // Example for current year
        when (yearMonth.monthValue) {
            1 -> holidays.add(PublicHoliday(LocalDate.of(yearMonth.year, 1, 1), "New Year's Day"))
            5 -> holidays.add(PublicHoliday(LocalDate.of(yearMonth.year, 5, 1), "Labour Day"))
            12 -> holidays.add(PublicHoliday(LocalDate.of(yearMonth.year, 12, 25), "Christmas Day"))
        }
    }
    return holidays
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun attendanceScreen(navController: NavHostController,
                     //onHome: () -> Unit): () -> Unit //Assuming onHome is not strictly needed
){
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(6) } // Adjust range
    val endMonth = remember { currentMonth.plusMonths(6) }   // Adjust range
    val daysOfWeekList = remember { daysOfWeek() }

    val holidaysInScope = remember { mutableStateListOf<PublicHoliday>() }
    val attendanceRecordsInScope = remember { mutableStateListOf<AttendanceRecord>() }
    val coroutineScope = rememberCoroutineScope()

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) } // To show details for a selected date

    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeekList.first()
    )

    // Load holidays and attendance data when visible months change
    LaunchedEffect(calendarState.firstVisibleMonth, calendarState.lastVisibleMonth) {
        coroutineScope.launch {
            holidaysInScope.clear()
            attendanceRecordsInScope.clear()
            var month = calendarState.firstVisibleMonth
            val last = calendarState.lastVisibleMonth
            while (month <= last) {
                holidaysInScope.addAll(getPublicHolidays(month))
                attendanceRecordsInScope.addAll(getStudentAttendanceForMonth(month)) // Fetch attendance
                month = month.plusMonths(1)
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Attendance - ${calendarState.firstVisibleMonth.year}") }, // Correct way to set the title
                navigationIcon = { // Use navigationIcon for the back button
                    IconButton(onClick = { navController.navigate("Home") }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate to Home"
                        )
                    }
                }
            )

        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Text("Attendance")
                DaysOfWeekTitle(daysOfWeek = daysOfWeekList)
                Spacer(modifier = Modifier.height(16.dp))

                HorizontalCalendar(
                    state = calendarState,
                    dayContent = { day ->
                        val attendance = attendanceRecordsInScope.find { it.date == day.date }
                        val holiday =
                            holidaysInScope.find { it.date == day.date && day.position == DayPosition.MonthDate }
                        DayView(
                            day = day,
                            attendance = attendance,
                            holiday = holiday,
                            isSelected = selectedDate == day.date && day.position == DayPosition.MonthDate,
                            onClick = { clickedDay ->
                                if (clickedDay.position == DayPosition.MonthDate) {
                                    selectedDate = clickedDay.date
                                }
                            }

                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                selectedDate?.let { date ->
                    val attendanceDetail = attendanceRecordsInScope.find { it.date == date }
                    val holidayDetail = holidaysInScope.find { it.date == date }

                    Text("Details for: ${date.dayOfMonth} ${date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())} ${date.year}", style = MaterialTheme.typography.titleMedium)

                    Spacer(modifier = Modifier.height(8.dp))

                    if (holidayDetail != null) {
                        Text(
                            "Status: Public Holiday (${holidayDetail.name})",
                            color = Color.Magenta
                        )
                    } else if (attendanceDetail != null) {
                        when (attendanceDetail.status) {
                            AttendanceStatus.PRESENT -> Text(
                                "Status: Present",
                                color = Color(0xFF4CAF50)
                            ) // Green
                            AttendanceStatus.ABSENT -> Text("Status: Absent", color = Color.Red)
                            AttendanceStatus.UNMARKED -> Text(
                                "Status: Not Yet Marked",
                                color = Color.Gray
                            )
                        }
                        attendanceDetail.reason?.let { reason ->
                            Text("Reason: $reason")
                        }
                    } else if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
                        Text("Status: Weekend")
                    } else {
                        Text("Status: No record for this day.")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Legend()  //Add a legend
            }
        }
    )
}
@Composable
fun DayView(
    day: CalendarDay,
    attendance: AttendanceRecord?,
    holiday: PublicHoliday?,
    isSelected: Boolean,
    onClick: (CalendarDay) -> Unit
) {
    val isToday = day.date == LocalDate.now() && day.position == DayPosition.MonthDate
    val isPast = day.date < LocalDate.now() && day.position == DayPosition.MonthDate
    val isWeekend = (day.date.dayOfWeek == DayOfWeek.SATURDAY || day.date.dayOfWeek == DayOfWeek.SUNDAY) && day.position == DayPosition.MonthDate


    val baseModifier = Modifier
        .aspectRatio(1f)
        .padding(2.dp)
        .clip(CircleShape)
        .conditionalClickable(enabled = day.position == DayPosition.MonthDate) {
            onClick(day)
        }

    val (cellColor, textColor, indicatorColor) = when {
        day.position != DayPosition.MonthDate -> Color.Transparent to MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f) to Color.Transparent // Out-dates
        isSelected -> MaterialTheme.colorScheme.primaryContainer to MaterialTheme.colorScheme.onPrimaryContainer to Color.Transparent
        holiday != null -> Color.Magenta.copy(alpha = 0.1f) to Color.Magenta to Color.Magenta.copy(alpha = 0.7f)
        attendance != null -> when (attendance.status) {
            AttendanceStatus.PRESENT -> Color(0xFF4CAF50).copy(alpha = 0.1f) to Color(0xFF388E3C) to Color(0xFF4CAF50) // Light Green bg, Darker Green text/indicator
            AttendanceStatus.ABSENT -> Color.Red.copy(alpha = 0.1f) to Color.Red to Color.Red.copy(alpha = 0.7f)
            AttendanceStatus.UNMARKED -> if(isToday) MaterialTheme.colorScheme.surfaceVariant to MaterialTheme.colorScheme.onSurfaceVariant to Color.Gray else Color.Transparent to MaterialTheme.colorScheme.onSurface to Color.Transparent
        }
        isToday -> MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f) to MaterialTheme.colorScheme.onTertiaryContainer to Color.Transparent
        isWeekend -> Color.LightGray.copy(alpha = 0.1f) to MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f) to Color.Transparent
        else -> Color.Transparent to MaterialTheme.colorScheme.onSurface to Color.Transparent
    }
    Box (
        modifier = baseModifier.background(cellColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
            fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
            if (holiday != null || (attendance?.status == AttendanceStatus.PRESENT || attendance?.status == AttendanceStatus.ABSENT) || isToday || isSelected) androidx . compose . ui . text . font . FontWeight.Bold else androidx . compose . ui . text . font . FontWeight.Normal
        )
        if (day.position == DayPosition.MonthDate && indicatorColor != Color.Transparent) {
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .padding(bottom= 4.dp)
                    .background(indicatorColor, CircleShape)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

fun Modifier.conditionalClickable(enabled: Boolean, onClick: () -> Unit): Modifier {
    return if (enabled) this.then(androidx.compose.foundation.clickable { onClick() }) else this
}

@Composable
fun Legend() {
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text("Legend", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LegendItem(color = Color(0xFF4CAF50).copy(alpha = 0.7f), text = "Present")
        LegendItem(color = Color.Red.copy(alpha = 0.7f), text = "Absent / Missed")
        LegendItem(color = Color.Magenta.copy(alpha = 0.7f), text = "Public Holiday")
        LegendItem(color = Color.Gray, text = "Not Yet Marked / Future")
        LegendItem(
            color = MaterialTheme.colorScheme.primaryContainer,
            text = "Selected Day",
            isBorder = true
        )
        LegendItem(
            color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
            text = "Today",
            isBorder = true
        )
    }
}

@Composable
fun LegendItem(color: Color, text: String, isBorder: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 2.dp)) {
        Box(modifier = Modifier
            .size(10.dp)
            .background(if(isBorder) Color.Transparent else color, CircleShape)
            .then(if(isBorder) androidx.compose.foundation.border(1.dp, color, CircleShape) else Modifier)
        )
        Spacer(modifier = Modifier.padding(start = 6.dp))
        Text(text, fontSize = 12.sp)
    }
}

