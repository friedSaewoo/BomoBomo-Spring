$(document).ready(function(){
    $('#summernote').summernote({
    placeholder: '내용을 입력하세요',
    tabsize: 2,
    width:874,
    height: 500,
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
