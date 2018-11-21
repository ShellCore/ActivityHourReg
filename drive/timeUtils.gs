function getDay(date) {
    return Utilities.formatDate(date, "GMT", "d");
}

function formatYearMonth(dateStr) {
    // Nov 20, 2018 16:13:44
    Logger.log(dateStr);

    var date = new Date(dateStr);
    
    var dateFormatted = Utilities.formatDate(date, "GTM", "yyyy-MM");
    return dateFormatted;
}