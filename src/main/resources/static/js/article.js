// 삭제 기능
const articleDelete = document.getElementById('delete-btn');

if (articleDelete) {
    articleDelete.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        function success() {
            location.replace('/articles');
        }
        function fail() {
            location.reload();
        }
        httpRequest('DELETE', `/api/articles/${id}`, null, success, fail);
    });
}
// 검색 기능
const searchBtn = document.getElementById('search-btn');
if(searchBtn){
    searchBtn.addEventListener('click', function() {
        var keyword = document.getElementById('searchInput').value;
        sessionStorage.setItem('previousPage', window.location.pathname);
        window.location.href = '/articles?keyword=' + encodeURIComponent(keyword);
    });
}
const modifyBtn = document.getElementById('modify-btn');
if(modifyBtn){
    modifyBtn.addEventListener('click', function() {
        let id = document.getElementById('article-id').value;
        body = JSON.stringify({ //JavaScript 객체나 배열을 JSON 문자열로 변환하는 메서드입니다
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        });
        function success() {
            alert('성공');
            location.replace(`/articles/${id}`);
        }
        function fail() {
            alert('ERROR');
            location.replace(`/articles/${id}`);
        }
        httpRequest('PUT',`/api/articles/${id}`,body, success, fail);
    });
}

//이전 주소 저장
const writeBtn = document.getElementById('write-btn');
if(writeBtn){
    writeBtn.addEventListener('click', function() {
        // 현재 페이지의 경로를 세션 스토리지에 저장
        sessionStorage.setItem('previousPage', window.location.pathname);
        window.location.href = '/new-article';
    });
}

document.querySelectorAll('.article-image').forEach(function(image) {
    image.addEventListener('click', function() {
    // 모달의 이미지 경로를 클릭된 이미지의 경로로 설정
        var modalImage = document.getElementById('modal-image');
        modalImage.src = this.src;

    // 모달을 화면에 표시
        var modal = document.getElementById('image-modal');
        modal.style.display = 'flex';
    });
});

    // 모달 닫기 버튼 처리
document.querySelector('.close-modal').addEventListener('click', function() {
    document.getElementById('image-modal').style.display = 'none';
});

    // 모달 백그라운드 클릭 시 모달 닫기
document.querySelector('.modal-background').addEventListener('click', function() {
    this.style.display = 'none';
});

// 좋아요 기능
const likeUpButton = document.getElementById('LikeUp-btn');
if (likeUpButton) {
    likeUpButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        function success(){
            location.reload();
        }
        function fail(){
            location.reload();
        }
        httpRequest('POST',`/api/likes/up/${id}`, null, success, fail)
    });
}

// 쿠키를 가져오는 함수
//function getCookie(key) {
//    var result = null;
//    var cookie = document.cookie.split(';');
//    cookie.some(function (item) {
//        item = item.replace(' ', '');
//
//        var dic = item.split('=');
//
//        if (key === dic[0]) {
//            result = dic[1];
//            return true;
//        }
//    });
//
//    return result;
//}

// HTTP 요청을 보내는 함수
function httpRequest(method, url, body, success, fail) {
    fetch(url, {
        method: method,
        headers: { // 로컬 스토리지에서 액세스 토큰 값을 가져와 헤더에 추가
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
            'Content-Type': 'application/json'
        },
        body: body,
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return success();
        }
        else {
            alert(response.status);
            return fail();
        }
    });
}