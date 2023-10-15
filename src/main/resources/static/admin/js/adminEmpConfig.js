function displayResizedImage() {
    const imageInput = document.getElementById('image-input');
    const canvas = document.getElementById('image-preview');
    const ctx = canvas.getContext('2d');

    if (imageInput.files && imageInput.files[0]) {
        const reader = new FileReader();

        reader.onload = function(e) {
            const img = new Image();
            img.src = e.target.result;

            // 원하는 크기로 이미지를 조절
            const maxWidth = 300; // 변경할 최대 너비
            const maxHeight = 300; // 변경할 최대 높이

            img.onload = function() {
                let newWidth = img.width;
                let newHeight = img.height;

                // 이미지 크기 조절
                if (img.width > maxWidth) {
                    newWidth = maxWidth;
                    newHeight = (img.height * maxWidth) / img.width;
                }

                if (newHeight > maxHeight) {
                    newHeight = maxHeight;
                    newWidth = (img.width * maxHeight) / img.height;
                }

                canvas.width = newWidth;
                canvas.height = newHeight;

                ctx.drawImage(img, 0, 0, newWidth, newHeight);
            };

            img.src = e.target.result;
        };

        reader.readAsDataURL(imageInput.files[0]);
    }
}

function loadImage() {
    let empImgUploadPath = document.getElementById('empImgUploadPath').value;
    let empImgUuid = document.getElementById('empImgUuid').value;
    let empImgName = document.getElementById('empImgName').value;
    let imagePath = empImgUploadPath + '/' + empImgUuid + '_' + empImgName;


    let canvas = document.getElementById('image-preview');
    let context = canvas.getContext('2d');
    let img = new Image();

    img.onload = function() {
        canvas.width = img.width;  // 이미지와 같은 크기로 캔버스 크기 설정
        canvas.height = img.height;
        context.drawImage(img, 0, 0);  // 이미지를 캔버스에 그립니다.
    };
    img.src = "/admin/rest/displayEmpImg?fileName=" + imagePath;
}
loadImage();

// 원하는 체크박스 갯수 제한
const maxChecked = 3; // 체크박수 갯수 제한 3개
const checkboxes = document.querySelectorAll('.act-check');

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', function() {
        const checkedCheckboxes = document.querySelectorAll('.act-check:checked');
        if (checkedCheckboxes.length > maxChecked) {
            this.checked = false;
        }
    });
});