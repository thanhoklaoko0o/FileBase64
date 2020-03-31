var baseService = "https://apivietnam.herokuapp.com";
var provinceUrl = baseService + "/province";
var districtUrl = baseService + "/districts";
var wardUrl = baseService + "/wards";
$(document).ready(function () {
	
	_getProvince();
	
    $("#ddlProvince").on('change', function () {
        var id = $(this).val();
        if (id != undefined && id != '') {
            _getDistrict(id);
        }
    });
    $("#ddlDistrict").on('change', function () {
        var id = $(this).val();
        if (id != undefined && id != '') {
            _getWard(id);
        }
    });
});

function _getProvince() {
    $.get(provinceUrl, function (data) {
        if (data != null && data != undefined && data.length) {
            var html = '';
            html += '<option value="">--Chọn--</option>';
            $.each(data, function (key, item) {
                html += '<option value=' + item.code + '>' + item.name + '</option>';
            });
            $("#ddlProvince").html(html);
        }
    });
}

function _getDistrict(id) {
    $.get(districtUrl + "/" + id, function (data) {
        if (data != null && data != undefined && data.length) {
            var html = '';
            html += '<option value="">--Chọn--</option>';
            $.each(data, function (key, item) {
                html += '<option value=' + item.code + '>' + item.name + '</option>';
            });
            $("#ddlDistrict").html(html);
        }
    });
}


function _getWard(id) {
    $.get(wardUrl + "/" + id, function (data) {
        if (data != null && data != undefined && data.length) {
            var html = '';
            html += '<option value="">--Chọn--</option>';
            $.each(data, function (key, item) {
                html += '<option value=' + item.code + '>' + item.name + '</option>';
            });
            $("#ddlWard").html(html);
        }
    });
}