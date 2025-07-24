package io.dala.tester

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.time.LocalDate
import java.time.YearMonth

data class PublicHoliday(val date: LocalDate, val name: String)

@RequiresApi(Build.VERSION_CODES.O)
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

@SuppressLint("RememberReturnType")
@Composable
fun attendanceScreen(navController: NavHostController){

}

fun Modifier.conditionalClickable(enabled: Boolean, onClick: () -> Unit): Modifier {
    return if (enabled) this.then(Modifier.clickable { onClick() }) else this
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
            .then(if(isBorder) Modifier.border(1.dp, color, CircleShape) else Modifier)
        )
        Spacer(modifier = Modifier.padding(start = 6.dp))
        Text(text, fontSize = 12.sp)
    }
}

