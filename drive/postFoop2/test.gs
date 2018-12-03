function testSaveData() {
    try {
        var jsonString = '{"foop2" : {"application" : "Produce Android, Expediente RH","description" : "Produce y Expediente RH","descriptionLarge" : "Actualización de aplicaciones y adición de componentes generados por el cliente para liberación.","justification" : "Estandarizacón de componentes para que el área de integración del cliente pueda generar las aplicaciones productivas."},"client" : {"client" : "David Márquez Hernandez","contact" : "Elsa Paula Gutiérrez","assignKey" : "FD1801-ASIGIOAN"},"user" : {"name" : "Cesar Morales Garduño","userId" : "MOGC"}}';
        var json = JSON.parse(jsonString);
        saveData(json);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testCreatePDF() {
    try {
        var userId = "MOGC";
        var date = new Date();
        
        var path = createPdfFile(userId, date);
        Logger.log(path);
    } catch (ex) {
        Logger.log(ex);
    }
}

function testGetShareUrl() {
    var path = "FOOP 002 MOGC 2018-12.pdf";
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
    var dateStr = new Date();
    var fileName = obtenerNombreArchivo(userId, dateStr);
    Logger.log(fileName)
}

function testCleanData() {
    cleanData();
}