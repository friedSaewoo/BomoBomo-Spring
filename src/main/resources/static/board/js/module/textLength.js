export function getTextLength() {
    let contents = $('#reply-content').val();

    let len = 0;
    for (let i = 0; i < contents.length; i++) {
        if (escape(contents.charAt(i)).length == 6) {
            len++;
        }
        len++;
    }
    return len;
}