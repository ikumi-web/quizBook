/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {

	const humbergerButton = document.querySelector('.menu');
	humbergerButton.addEventListener('click', () => {
		humbergerButton.classList.toggle('menu-open');
	})
	//多肢選択の際の背景変化用▼▼▼▼▼▼▼▼
	const optionButton = document.querySelectorAll('.optionButton');
	optionButton.forEach(pushOptionButton);

	function pushOptionButton(button) {
		button.addEventListener('click', () => {
			button.classList.toggle('push-optionButton');
		})
	}
	//多肢選択の際の背景変化用▲▲▲▲▲▲▲▲
	//多肢選択の際のForm送信用▼▼▼▼▼▼▼▼
	const submitButtonContainer = document.querySelector('#submitButtonContainer');
	const submitButton = document.querySelector('#submitButton');
	const selectOptions = new Array();

	submitButton.addEventListener('click', () => {
		//選択肢の一番その都のdivタグを全て取得
		const optionButtons = document.querySelectorAll('.optionButtonContainer');
		//1つ1つ取り出してdivのbutton要素の中のクラスを調べる
		optionButtons.forEach((optionButton,index) => {
			const button = optionButton.querySelector('button');
				if (button.classList.contains('push-optionButton')) {
					//選択肢の個別のIdを取得
					const option = optionButton.querySelector(`input[id="optionId-${index}"]`);
					//選択肢のidをSetに格納。消さないので増え続けるけど送信するので問題ない？
					selectOptions.push(option.value);
				}
		})
		for(let i = 0; i<selectOptions.length;i++){
			const optionId = document.createElement('input');
			optionId.type = 'hidden';
			optionId.name = 'optionId';
			optionId.value = selectOptions[i];
			submitButtonContainer.appendChild(optionId);
		}
		submitButtonContainer.submit();
	})
	//多肢選択の際のForm送信用▲▲▲▲▲▲▲▲
	
	
});