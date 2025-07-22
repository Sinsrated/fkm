import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

enum class AttendanceStatus {
    PRESENT, ABSENT, UNMARKED //UNMARKED
}
data class AttendanceRecord(
    val date: LocalDate,
    val status: AttendanceStatus,
    val reason: String? = null // Optional reason for absence, etc.
)


fun getStudentAttendanceForMonth(yearMonth: YearMonth): List<AttendanceRecord> {

    val records = mutableListOf<AttendanceRecord>()
    val today = LocalDate.now()

    if  (yearMonth == YearMonth.from(today)) {
        if (today.dayOfMonth > 1) {
            records.add(AttendanceRecord(today.minusDays(1), AttendanceStatus.PRESENT))

        }
        if ( today.dayOfMonth > 2) {
            records.add(AttendanceRecord(today.minusDays(2), AttendanceStatus.ABSENT, "Not feeling well"))
        }
        if (today.dayOfMonth > 3) {
            records.add(AttendanceRecord(today.minusDays(3), AttendanceStatus.PRESENT))
        }
        if (today.dayOfMonth > 4 && today.dayOfWeek != DayOfWeek.SATURDAY && today.dayOfWeek != DayOfWeek.SUNDAY) {
            // Avoid marking weekends as absent if that's your logic
            records.add(AttendanceRecord(today.minusDays(4), AttendanceStatus.ABSENT, "Appointment"))
        }
        records.add(AttendanceRecord(today, AttendanceStatus.UNMARKED)) // Example for today
    } else if (yearMonth == YearMonth.from(today.plusMonths(1))) {
        // Next Month Examples
        records.add(AttendanceRecord(LocalDate.of(yearMonth.year, yearMonth.month, 5), AttendanceStatus.PRESENT))
        records.add(AttendanceRecord(LocalDate.of(yearMonth.year, yearMonth.month, 12), AttendanceStatus.ABSENT))
    }
    return records

}