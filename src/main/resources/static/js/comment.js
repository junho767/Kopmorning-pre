
//댓글 작성
const writeComment = document.getElementById('writeComment');
if (writeComment) {
    writeComment.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        body = JSON.stringify({
            comment: document.getElementById('comment').value
        });

        function success() {
            location.reload();
        }
        function fail() {
            location.reload();
        }
       httpRequest('POST', `/api/articles/${id}/comment` , body, success, fail);
    });
}

//댓글 삭제
const deleteCommentButtons = document.querySelectorAll('.delete-comment-btn');
deleteCommentButtons.forEach(function(deleteCommentButton) {
    deleteCommentButton.addEventListener('click', function() {
        let id = this.parentElement.querySelector('.comment-id').value;
        swal.fire({
            title: "삭제하시겠습니까?",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#6495ed",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes"
        }).then((result) => {
            if (result.isConfirmed) {
                const body = JSON.stringify({ id: id });
                httpRequest('DELETE', `/api/comment/${id}`, body, success, fail);
            }
        });

        function success() {
            location.reload();
        }

        function fail() {
            alert('ERROR');
            location.reload();
        }
    });
});

//댓글 수정 [수정본]
document.addEventListener('DOMContentLoaded', function() {
    // 수정 버튼
    document.querySelectorAll('.modify-comment-btn').forEach(function(ModifyComment) {
        ModifyComment.addEventListener('click', function() {
            const deleteComment = this.parentElement.querySelector('.delete-comment-btn');
            const saveComment = this.parentElement.querySelector('.save-comment-btn');
            const cancelComment = this.parentElement.querySelector('.cancel-comment-btn');
            const commentContent = this.parentElement.querySelector('.comment-content');
            const commentInput = this.parentElement.querySelector('.comment-input');
            // 삭제 버튼과 수정 버튼 숨기기
            deleteComment.style.display = 'none';
            ModifyComment.style.display = 'none';
            saveComment.style.display = 'inline-block';
            cancelComment.style.display = 'inline-block';

            commentInput.value = commentContent.textContent;
            commentContent.style.display = 'none';
            commentInput.style.display = 'inline-block';
            commentInput.style.border = '1px solid #ccc';
            commentInput.style.borderRadius = '20px';
            commentInput.style.padding = '5px';
            commentInput.style.width = '500px';
        });
    });
    // 완료 버튼
    document.querySelectorAll('.save-comment-btn').forEach(function(saveComment) {
        saveComment.addEventListener('click', function() {
            let id = this.parentElement.querySelector('input[type="hidden"]').value;
            let Modifications = this.parentElement.querySelector('.comment-input').value;
            body = JSON.stringify({
               comment : Modifications,
               id : id
            });
            function success(){
                swal.fire({
                    title: "성공!",
                    icon: "success",
                    showCancelButton: false,
                    showConfirmButton: false
                });
                setTimeout(() => {
                    location.reload();
                }, 1000);
            }
            function fail(){
                alert("ERROR");
                location.reload();
            }
            httpRequest('PUT',`/api/comment/${id}`, body , success , fail);
        });
    });
    //취소 버튼
    document.querySelectorAll('.cancel-comment-btn').forEach(function(cancelComment) {
            cancelComment.addEventListener('click', function() {
            // 수정, 삭제 버튼 표시
            const modifyComment= this.parentElement.querySelector('.modify-comment-btn');
            const deleteComment = this.parentElement.querySelector('.delete-comment-btn');
            modifyComment.style.display = 'inline-block';
            deleteComment.style.display = 'inline-block';
            // 완료 버튼 숨김
            const saveComment = this.parentElement.querySelector('.save-comment-btn');
            saveComment.style.display = 'none';
            cancelComment.style.display= 'none';
            // 입력 필드의 내용 원래대로 되돌리기
            const commentContent = this.parentElement.querySelector('.comment-content');
            const commentInput = this.parentElement.querySelector('.comment-input');
            commentContent.style.display = 'inline-block';
            commentInput.style.display = 'none';
        });
    });
    //삭제 버튼
    document.querySelectorAll('.delete-comment-btn').forEach(function(deleteComment) {
        deleteComment.addEventListener('click', function() {
            let id = this.parentElement.querySelector('input[type="hidden"]').value;
            swal.fire({
                title: "삭제하시겠습니까?",
                icon: "question",
                showCancelButton: true,
                confirmButtonColor: "#6495ed",
                cancelButtonColor: "#d33",
                confirmButtonText: "Yes"
            }).then((result) => {
                if (result.isConfirmed) {
                    const body = JSON.stringify({ id: id });
                    httpRequest('DELETE', `/api/comment/${id}`, body, success, fail);
                }
            });

            function success() {
                location.reload();
            }

            function fail() {
                alert('ERROR');
                location.reload();
            }
        });
    });
});
function httpRequest(method, url, body, success, fail) {
    fetch(url, {
        method: method,
        headers: { // 로컬 스토리지에서 액세스 토큰 값을 가져와 헤더에 추가
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
            'Content-Type': 'application/json',
        },
        body: body,
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return success();
        }
        else {
            return fail();
        }
    });
}