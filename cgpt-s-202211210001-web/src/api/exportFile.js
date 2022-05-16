import FileSaver from "file-saver";
export default class fileSave {
    /**
     * 导出Excel文件
     * &#64;param {*} res   文件流
     * &#64;param {*} name  文件名
     */
    static getExcel(res, name) {
        let blob = new Blob([res], {
            type: "application/vnd.ms-excel"
        });
        FileSaver.saveAs(blob, name + ".xlsx");
    }

    /**
     * 导出CSV文件
     * &#64;param {*} res   文件流
     * &#64;param {*} name  文件名
     */
    static getCsv(res, name) {
        let blob =
            new Blob([res], {
                type: "application/vnd.ms-excel"
            });
        FileSaver.saveAs(blob, name + ".csv");
    }

    /**
     * 导出图片1
     * &#64;param {*} url 图片地址
     * &#64;param {*} name  文件名
     */
    static getImgURLs(url, name) {
            let last =
                url.substring(url.lastIndexOf("."), url.length);
            FileSaver.saveAs(url, name + last);
        }
        /**
         * 导出图片2
         * &#64;param {*} res 文件流
         * &#64;param {*} name  文件名
         */
    static downLoadImg(res, filename) {
        let blob =
            new Blob([res], {
                type: "image/jpeg"
            });
        FileSaver.saveAs(blob, filename + ".jpg");
    }

    static getTxt(res, filename) {
        let blob = new Blob([res], { type: 'text/plain;charset=utf-8' });
        FileSaver.saveAs(blob, filename + ".txt ");
    }
}