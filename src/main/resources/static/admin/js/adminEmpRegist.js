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

$(".city-select").change(function () {
    let cityNumber = $(this).val();
    $.ajax({
        url: `/admin/rest/country`,
        type: 'get',
        data :{cityNumber:cityNumber} ,
        dataType: 'json',
        success: function (result) {
            console.log(result);

            loadCountry(result);
        },
        error: function (a, b, c) {
            console.log("실패");
            console.error(c);
        }
    });
});
function loadCountry(result) {
    let select = $(".country-select");
    select.empty();
    select.append('<option value="0">----</option>');
    $.each(result, function (index, country) {
        select.append('<option value="' + country.countryNumber + '">' + country.countryName + '</option>');
    });
}

$(document).ready(function(){
    $('#summernote').summernote({
        placeholder: '내용을 입력하세요',
        tabsize: 2,
        width:750,
        height: 400,
        disableResizeEditor:true,
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link','codeview', 'help']]
        ]
    });
});