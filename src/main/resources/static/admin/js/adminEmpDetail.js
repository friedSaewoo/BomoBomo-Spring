let empNumber = document.getElementById('empNumber').value;
console.log(empNumber);
loadPage(empNumber);

function loadImage() {
    let empImgUploadPath = document.getElementById('empImgUploadPath').value;
    let empImgUuid = document.getElementById('empImgUuid').value;
    let empImgName = document.getElementById('empImgName').value;
    let imagePath = empImgUploadPath + '/' + empImgUuid + '_' + empImgName;

    console.log(empImgUploadPath);
    console.log(empImgName);
    console.log(empImgUuid);
    let img = new Image();
    img.onload = function() {
        img.className = "self";
        let empImgDiv = $('<div class=".emp-info-container1">');
        empImgDiv.append(img);

        document.querySelector('.img').appendChild(empImgDiv[0]);
    };
    img.src = "/admin/rest/displayEmpImg?fileName=" + imagePath;
}

loadImage();

function loadPage(empNumber) {
    $.ajax({
        url: `/admin/rest/actImg`,
        type: 'get',
        data : { empNumber: empNumber },
        dataType: 'json',
        success: function (result) {
            console.log(result);
        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}

// actList 받아오기까지 완료 html에 붙여주기부터 시작