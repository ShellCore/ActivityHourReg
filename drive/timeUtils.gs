function getDay(date) {
    return Utilities.formatDate(date, "GMT", "d");
}

function formatYearMonth(dateStr) {
    // yyyy-MM-dd
    Logger.log(dateStr);
    
    var year = +dateStr.substring(0, 4);
    var month = +dateStr.substring(5, 7);

    var date = new Date(year, month-1, 1);
    
    var dateFormatted = Utilities.formatDate(date, "GTM", "yyyy-MM");
    return dateFormatted;
}