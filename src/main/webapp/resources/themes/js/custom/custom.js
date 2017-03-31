
(function ($) {
    $(document).ready(function () {
        menuActivate();
    });
    menuActivate = function () {
        $('.sidebar-menu li').removeClass('active');
        urlArr = window.location.href.toString().split("/");
        if (typeof urlArr[5] != "undefined") {
            $('#' + urlArr[5] + 'Menu').addClass('active');
            $('#' + urlArr[5] + 'Menu').parent().parent().addClass('active');
        } else {
            $('#' + urlArr[4] + 'Menu').addClass('active');
        }
        if (typeof urlArr[6] != "undefined") {
            $('#' + urlArr[6] + 'Menu').addClass('active');
            $('#' + urlArr[6] + 'Menu').parent().parent().addClass('active');
        }
        console.log(urlArr);
    };
    doLoadForm = function (arg) {
        $.ajax({
            url: arg[0],
            success: function (resp) {
                $("#modelCnt").empty().append(resp);
                $("#modelHead").empty().html(arg[2]);
                $('#' + arg[1]).modal();
            }
        });
    };
    doAjaxUserPost = function (url, formId) {
        console.log($("#" + formId).serialize());
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            data: $("#" + formId).serialize(),
            success: function (resp) {
                console.log(resp);
                if (resp.status == "FAIL") {
                    $('[id^="error-"]').empty();
                    for (i = 0; i < resp.result.length; i++) {
                        $("#" + resp.result[i].code).html(resp.result[i].defaultMessage)
                    }
                } else {
                    $('[id^="error-"]').empty();
                    window.top.location.reload();
                }
            }
        });
    };
    showPassword = function () {
        $('#passShow1').toggle();
        $('#passShow2').toggle();
    }
    changingEnterKeyToTab = function () {
        $("input").not($(":button")).keypress(function (evt) {
            if (evt.keyCode == 13) {
                iname = $(this).val();
                if (iname !== 'Submit') {
                    var fields = $(this).parents('form:eq(0),body').find('button, input, textarea, select');
                    var index = fields.index(this);
                    if (index > -1 && (index + 1) < fields.length) {
                        fields.eq(index + 1).focus();
                    }
                    return false;
                }
            }
        });
    }

    addNewRow = function (cloneId, cloneToId) {
        var i = Number($('#'+cloneToId+' tbody').children('tr').length) + 1;
        var cloned = $("#" + cloneId + " tbody tr:last").clone(true);
        cloned.find("td:last").empty();
        cloned.find("select").each(function () {
            $(this).attr({
                'id': function (_, id) {
                    return id + i
                },
                'name': function (_, name) {
                    return name.replace("[0]", "[" + i + "]");
                },
                'tabindex': '-1',
                'value': ''
            });
        });
        cloned.find("input").each(function () {
            $(this).attr({
                'id': function (_, id) {
                    return id + i
                },
                'name': function (_, name) {
                    return name.replace("[0]", "[" + i + "]")
                },
                'tabindex': '-1',
                'value': ''
            });
        });
        cloned.attr({
            'id': function (_, id) {
                return id.replace("0",i );
            }
        });
        
        $("#" + cloneId + " tbody tr:last").find("select").each(function () {
            cloned.find('select[id=' + this.id + i + ']').val(this.value);
        });
        $("#" + cloneId + " tbody tr:last").find("input").each(function () {
            valueIp = this.value;
            if (isNumber(this.value)) {
                valueIp = toDecimal(this.value);
            }
            cloned.find(':input[id=' + this.id + i + ']').val(valueIp);
        });
        $("#" + cloneToId + " tbody").append(cloned);
        i++;
        $("#" + cloneId + " tbody tr:last").find("select").each(function () {
            $(this).val(0);
        });
        $("#" + cloneId + " tbody tr:last").find("input").each(function () {
            $(this).val(0);
        });
        $("select[id^='prodCode']").change(function () {
            id = $(this).attr('id');
            var lastChar = id.substr(id.length - 1);
            $("#prodName" + lastChar).val($(this).val());
        });


    }
    isNumber = function (n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }
    toDecimal = function (n) {
        return parseFloat(Math.round(n * 100) / 100).toFixed(2);
    }

    sumtotal = function (cloneToId, id) {
        setTimeout(function () {

            var sum = 0;
            $("#" + cloneToId + " input[class^='price']").each(function () {
                sum += parseFloat($(this).val());
            });
            $('#' + id).val(toDecimal(sum));
        }, 300);

    }
    focuseTo = function (focuseId) {
        $('#' + focuseId).focus();
    }
    calRowTotal = function (object) {
        var row = $("#"+object.id).parent().parent().attr("id");
        alert(row);
        qt = parseFloat($("#"+row+" input[id^=prodQty]").val());
        pr = parseFloat($("#"+row+" input[id^=prodPrice]").val());
        //qt = parseFloat($('#prodQty').val());
       //pr = parseFloat($('#prodPrice').val());
        $("#"+row+" input[id^=rowTotal]").val(toDecimal(qt * pr));
    }
    sumOrderTotal = function (id) {
        setTimeout(function () {
            var sum = 0;
            $(".sumTotal").each(function () {
                if (isNumber($(this).val())) {
                    sum += parseFloat($(this).val());
                }
            });
            $('#' + id).val(toDecimal(sum));
        }, 300);
    }
    getStates = function () {
        urlVal = "../../statesls/listbycountry/" + $("#country").val();
            alert("comes:"+urlVal);
            $.ajax({
                url: urlVal,
                success: function (resp) {
                    console.log(resp);
                    $("#stateLists").append(resp);
                }
            });
        }
    getMrp = function(){
        var sub_total_1 = 0;
        var include_margin = 0;
        var include_tax = 0;
        var grand_total = 0;
        $("input[id^=mrp_]").not("#mrp_tax,#mrp_mweight,#mrp_margin").each(function(){
            console.log("Id for sum",$(this).attr('id'));
            sub_total_1 += parseFloat($(this).val());
        });
        include_margin = (sub_total_1 * parseFloat($("#mrp_margin").val()))/100;
        sub_total_1 = sub_total_1+include_margin;
        console.log("Margin Included:",sub_total_1);
        tx_val = $("#mrp_tax").val();
        tax =$("#mrp_tax option[value='"+tx_val+"']").text().split(":");
        include_tax = (sub_total_1 * parseFloat(tax[1]))/100;
        //console.log("Tax Included:",sub_total_1);
        grand_total = sub_total_1 + include_tax;
        $("#totalmrp").val(grand_total);
        console.log("Sub Total:",sub_total_1);
    }

})(jQuery);