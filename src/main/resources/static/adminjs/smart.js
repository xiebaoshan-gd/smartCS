//给Date对象扩展一个方法，该方法可以把Date对象转换成中国格式的日期
Date.prototype.print = function() {
	var year = this.getFullYear(); //获取年份
	var month = this.getMonth() + 1; //获取月份
	if (month < 10) {
		month = "0" + d.getMonth();
	}
	var date = this.getDate(); //获取日
	if (date < 10) {
		date = "0" + date;
	}
	var hour = this.getHours(); //获取小时
	if (hour < 10) {
		hour = "0" + hour;
	}
	var min = this.getMinutes(); //获取分钟
	if (min < 10) {
		min = "0" + min;
	}
	var sec = this.getSeconds(); //获取秒
	if (sec < 10) {
		sec = "0" + sec;
	}
	return year + "年" + month + "月" + date + "日 " + hour + ":" + min + ":" + sec;
}