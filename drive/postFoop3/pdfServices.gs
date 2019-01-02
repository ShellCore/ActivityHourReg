function createPdfPath(userId, date) {
    try {
        var pdfPath = createPdfFile(userId, date);
        var url = getFileUrl(pdfPath);
        return url;
    } catch (ex) {
        throw (ex);
    }
}