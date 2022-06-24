/*
formatDate(); // 2016-09-02 13:17:13
formatDate(new Date(), 'yyyy-MM-dd'); // 2016-09-02
formatDate(new Date(), 'yyyy-MM-dd 第q季度 www HH:mm:ss:SSS');// 2016-09-02 第3季度 星期五 13:19:15:792
formatDate(1472793615764); // 2016-09-02 13:20:15
*/

function formatDate(date, fmt) {
    date = date == undefined ? new Date() : date;
    date = typeof date == 'number' ? new Date(date) : date;
    fmt = fmt || 'yyyy-MM-dd HH:mm:ss';

    var obj = {
        'y': new Date(date).getFullYear(), // 年份，注意必须用getFullYear
        'M': new Date(date).getMonth() + 1, // 月份，注意是从0-11
        'd': new Date(date).getDate(), // 日期
        'q': Math.floor((new Date(date).getMonth() + 3) / 3), // 季度
        'w': new Date(date).getDay(), // 星期，注意是0-6
        'H': new Date(date).getHours(), // 24小时制
        'h': new Date(date).getHours() % 12 == 0 ? 12 : new Date(date).getHours() % 12, // 12小时制
        'm': new Date(date).getMinutes(), // 分钟
        's': new Date(date).getSeconds(), // 秒
        'S': new Date(date).getMilliseconds() // 毫秒
    };
    var week = ['天', '一', '二', '三', '四', '五', '六'];
    for (var i in obj) {
        //RegExp正則，g 多次
        fmt = fmt.replace(new RegExp(i + '+', 'g'), function(m) {
            var val = obj[i] + '';
            if (i == 'w') return (m.length > 2 ? '星期' : '周') + week[val];
            for (var j = 0, len = val.length; j < m.length - len; j++) val = '0' + val;
            return m.length == 1 ? val : val.substring(val.length - m.length);
        });
    }
    return fmt;
}

/**計算天數 */
function countDays(date1, date2) {

    var fmt = 'yyyy-MM-dd';
    // 将日期转换成字符串，转换的目的是去除“时、分、秒”
    if (date1 instanceof Date && date2 instanceof Date) {
        date1 = formatDate(date1, fmt);
        date2 = formatDate(date2, fmt);
    }
    if (typeof date1 === 'string' && typeof date2 === 'string') {
        date1 = Date.parse(date1);
        date2 = Date.parse(date2);
        return (new Date(date1).getTime() - new Date(date2).getTime()) / (1000 * 60 * 60 * 24);
    } else {
        console.error('参数格式无效！');
        return 0;
    }
}

/**
 * 獲取指定月份的天數
 * 方式一：$.getMonthDays();
 * 方式二：$.getMonthDays(new Date());
 * 方式三：$.getMonthDays(2013, 12);
 */
function getMonthDays(date, month) {
    var y, m;
    if (date == undefined) date = new Date();
    if (date instanceof Date) {
        y = date.getFullYear();
        m = date.getMonth();
    } else if (typeof date == 'number') {
        y = date;
        m = month - 1;
    }
    var days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // 非闰年的一年中每个月份的天数
    //如果是闰年并且是2月
    if (m == 1 && this.isLeapYear(y)) return days[m] + 1;
    return days[m];
}


/**判斷是否為閏年 */
function isLeapYear(year) {
    if (year === undefined) year = new Date();
    if (year instanceof Date) year = year.getFullYear();
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
}

/**
 * 秒轉換時間
 * 147->2分27秒
 */
function formatDurationToFriendly(second) {
    if (second < 60) return second + '秒';
    else if (second < 60 * 60) return (second - second % 60) / 60 + '分' + second % 60 + '秒';
    else if (second < 60 * 60 * 24) return (second - second % 3600) / 60 / 60 + '小时' + Math.round(second % 3600 / 60) + '分';
    return (second / 60 / 60 / 24).toFixed(1) + '天';
}

/**
 * 字串轉時間
 * @param str 字串，如'2014-09-13'
 * @param fmt 字串格是，默認'yyyy-MM-dd'
 */
function parseDate(str, fmt) {
    fmt = fmt || 'yyyy-MM-dd';
    var obj = { y: 0, M: 1, d: 0, H: 0, h: 0, m: 0, s: 0, S: 0 };
    fmt.replace(/([^yMdHmsS]*?)(([yMdHmsS])\3*)([^yMdHmsS]*?)/g, function(m, $1, $2, $3, $4) {
        str = str.replace(new RegExp($1 + '(\\d{' + $2.length + '})' + $4), function(_m, _$1) {
            obj[$3] = parseInt(_$1);
            return '';
        });
        return '';
    });
    obj.M--; // 月份是从0开始的，所以要减去1
    var date = new Date(obj.y, obj.M, obj.d, obj.H, obj.m, obj.s);
    if (obj.S !== 0) date.setMilliseconds(obj.S); // 如果设置了毫秒
    return date;
}

var dateReviver = function(key, value) {
    var a;
    if (typeof value === 'string') {
        a = /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value);
        if (a) {
            return new Date(Date.UTC(+a[1], +a[2] - 1, +a[3], +a[4], +a[5], +a[6]));
        }
    }
    return value;
};





export default {
    parseDate,
    formatDurationToFriendly,
    isLeapYear,
    getMonthDays,
    countDays,
    formatDate,
    dateReviver
}