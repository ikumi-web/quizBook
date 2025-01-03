

document.addEventListener('DOMContentLoaded', () => {
//	▼▼▼▼▼▼▼▼ハンバーガーメニュー用▼▼▼▼▼▼▼▼
	const humbergerButton = document.querySelector('.menu');
	humbergerButton.addEventListener('click',()=>{
		humbergerButton.classList.toggle('menu-open');
	});
//	▲▲▲▲▲▲▲▲ハンバーガーメニュー用▲▲▲▲▲▲▲▲
	
	const question = document.querySelectorAll('.question');
	const limit = 15; // 半角9文字指定

	question.forEach(function(question) {
		const str = question.textContent;
		if(str.length > limit) {
			question.textContent = str.substring(0, limit) + '...';
		}
	});
	const code = document.querySelectorAll('.code');
	code.forEach((code)=>{
		const str = code.textContent;
		if(str.length > limit){
			code.textContent = str.substring(0,limit)+'...';
		}
	})
	
	
});