String.prototype.trim = function() {
		var TRIM_PATTERN = /(^\s*)|(\s*$)/g;
		return this.replace(TRIM_PATTERN, "");
};

function isValidphone(phone) {
	var format= /^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/;
    if (phone.search(format) != -1)
        return true; //올바른 포맷 형식
    return false;
};

