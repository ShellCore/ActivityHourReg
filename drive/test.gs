function testSetDay() {
    var jsonString = '{"user": {"name" : "Cesar Morales","userId" : "MOGC"},"client": {"client" : "Elsa Paula Martínez","company" : "Afore Profuturo"},"month": [{"day" : "2018-11-07","begin" : "09:00","food" : "13:30","work" : "14:30","end" : "18:00","comments" : "comentario de prueba 1"},{"day" : "2018-11-09","begin" : "09:10","food" : "13:40","work" : "14:40","end" : "18:10","comments" : "comentario de prueba 2"}]}';
    var json = JSON.parse(jsonString);
    saveData(json);
}

function testCreatePDF() {
    try {
        var path = createPdfFile();
        Logger.log(path);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testGetShareUrl() {
    var path = "Asistencia noviembre 2018.pdf";
    Logger.log(path);
    try {
        var url = getFileUrl(path);
        Logger.log(url);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testObtenerNombreArchivo() {
    var userId = "MOGC";
    var dateStr = "2018-11-10";
    var fileName = obtenerNombreArchivo(userId, dateStr);
    Logger.log(fileName)
}