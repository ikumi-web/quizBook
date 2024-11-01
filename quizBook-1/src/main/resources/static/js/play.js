/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	const optionButton = document.querySelectorAll('.optionButton');
	const optionButtons = document.querySelectorAll('.optionButtonContainer');
	const humbergerButton = document.querySelector('.menu');
	const submitButtonContainer = document.querySelector('#submitButtonContainer');
	const submitButton = document.querySelector('#submitButton');
	const correctCountElement = document.querySelector('#correctCount');
	submitButton.disabled = true;


	//	▼▼▼▼▼▼▼▼ハンバーガーメニュー用▼▼▼▼▼▼▼▼
	humbergerButton.addEventListener('click', () => {
		humbergerButton.classList.toggle('menu-open');
	})
	//	▲▲▲▲▲▲▲▲ハンバーガーメニュー用▲▲▲▲▲▲▲▲
	//多肢選択の際の背景変化用▼▼▼▼▼▼▼▼
	optionButton.forEach(pushOptionButton);

	function pushOptionButton(button) {
		button.addEventListener('click', () => {
			button.classList.toggle('push-optionButton');
		})
	}
	//多肢選択の際の背景変化用▲▲▲▲▲▲▲▲
	//多肢選択の際のForm送信用▼▼▼▼▼▼▼▼
	const selectOptions = new Array();

	submitButton.addEventListener('click', () => {
		//選択肢の一番外のdivタグを全て取得
		//1つ1つ取り出してdivのbutton要素の中のクラスを調べる
		optionButtons.forEach((optionButton, index) => {
			const button = optionButton.querySelector('button');
			if (button.classList.contains('push-optionButton')) {
				//選択肢の個別のIdを取得
				const option = optionButton.querySelector(`input[id="optionId-${index}"]`);
				//選択肢のidをSetに格納。消さないので増え続けるけど送信するので問題ない？
				selectOptions.push(option.value);
			}
		})
		for (let i = 0; i < selectOptions.length; i++) {
			const optionId = document.createElement('input');
			optionId.type = 'hidden';
			optionId.name = 'optionId';
			optionId.value = selectOptions[i];
			submitButtonContainer.appendChild(optionId);
		}
		submitButtonContainer.submit();
	})
	//多肢選択の際のForm送信用▲▲▲▲▲▲▲▲
	//選択肢のボタンを選択が必要な数まで押さないと解答できないようにする▼▼▼▼▼▼▼▼
	let pushCount = 0;
	//解答が必要な数を取得
	const correctCount = parseInt(correctCountElement.textContent);
	optionButtons.forEach((optionButton) => {
		optionButton.addEventListener('click', () => {
			const button = optionButton.querySelector('button');
			if (button.classList.contains('push-optionButton')) {
				pushCount++;
			}else{
				pushCount--;
			}
			if(pushCount === correctCount){
				submitButton.disabled = false;
			}else{
				submitButton.disabled = true;
				
			}
		})
	})
	//選択肢のボタンを最低1つ押さないと解答できないようにする▲▲▲▲▲▲▲▲


});