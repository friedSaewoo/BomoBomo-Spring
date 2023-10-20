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
        let empImgDiv = $('<div class=".img">');
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
            console.log(result);
            loadActImg(result)
        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
}

// actList 받아오기까지 완료 html에 붙여주기부터 시작
function loadActImg(result){

    if(result.adminEmpList!=0){
        let actImgContainer = $('.act-item-container');
        actImgContainer.empty();

        $.each(result, function (index, actImg) {
            // let imagePath = eventDetailUploadPath + '/' + eventDetailUuid + '_' + eventDetailName;
            let imagePath = actImg.actImgUploadPath + '/' + actImg.actImgUuid +'_' + actImg.actImgName;
            let actDiv = $('<li class="act-item">');
            actDiv.append('<img src="/admin/rest/displayActImg?fileName=' + imagePath + '" class="act-img">');
            actDiv.append('<p>' + actImg.actName + '</p>');
            actImgContainer.append(actDiv);
        });
    }else{
        let empList = $('.emp-list');
        empList.empty();
    }
}


document.getElementById('board-delete').addEventListener('click', function(e) {
    e.preventDefault();
    console.log(empNumber);
    if (confirm('정말 삭제하시겠습니까?')) {
        window.location.href = "/admin/adminEmpDelete?empNumber=" + empNumber;
    } else {
    }
});