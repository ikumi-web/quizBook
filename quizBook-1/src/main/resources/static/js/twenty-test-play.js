


document.addEventListener("DOMContentLoaded", () => {

	//Are you Ready?Go!の文字表示▼▼▼▼▼▼▼▼
	const messageDiv = document.querySelector(".message");
	setTimeout(() => {
		messageDiv.style.display = 'none';
	}, 5000);
	messageDiv.innerHTML = 'Are you Ready?';
	messageDiv.style.display = 'block';
	setTimeout(() => {
		messageDiv.innerHTML = 'Go!!!';
	}, 3000);
	const messageBackDiv = document.querySelector(".message-back");
	setTimeout(() => {
		messageBackDiv.style.display = 'none';
	}, 5000);
	//Are you Ready?Go!の文字表示▲▲▲▲▲▲▲▲
	const submitContainer = document.querySelector('.submitContainer');
	setTimeout(() => {
		submitContainer.classList.remove('quizText');
	}, 5000);

	const minutesElem = document.querySelector(".minutes");
	const secondsElem = document.querySelector(".seconds");
	let minutes = parseInt(minutesElem.textContent, 10);
	let seconds = parseInt(secondsElem.textContent, 10);
	const secondsMill = minutes * 60 * 1000;

	function countdown() {
		if (minutes > 0 || seconds > 0) {
			if (seconds === 0) {
				minutes--;
				seconds = 59;
			} else {
				seconds--;
			}
			minutesElem.textContent = minutes < 10 ? '0' + minutes : minutes;
			secondsElem.textContent = seconds < 10 ? '0' + seconds : seconds;
		} else {
			console.log("countdown終了");
		}
	}
	//カウントダウン。カウントダウン後止まる
	setTimeout(() => {
		const interval = setInterval(countdown, 1000)
		setInterval(() => {
			clearInterval(interval);
		}, secondsMill);
	}, 4000);
	//	時間超過後、採点ボタンしか押せなくする
	const answer = document.querySelector(".answer");
	const answerTimeupText = document.querySelector(".answer-timeup-text");
	setTimeout(() => {
		answer.classList.add("answer-timeup");
		answerTimeupText.classList.remove("timeup-text");
	}, secondsMill + 5000);
	//ボタンをクリックしたらクラスをtoggleする
	const optionButton = document.querySelectorAll(".optionButton");
	optionButton.forEach(pushOptionButton);

	function pushOptionButton(button) {
		button.addEventListener('click', () => {
			button.classList.toggle('push-optionButton');
		})
	}
	//採点ボタンを押した際の振る舞い
	answerButton.addEventListener("click", () => {
		if (!(minutes === 0 && seconds === 0)) {
			if (confirm("時間が残っていますがテストを終了しますか？")) {
				selectOption();
			}
		} else {
			selectOption();
		}
	});
	//採点に必要な選択肢の抽出
	function selectOption() {
		const selectOptions = new Array();

		const optionButtonContainer = document.querySelectorAll(".optionButtonContainer");
		optionButtonContainer.forEach((optionButton, index) => {
			const button = optionButton.querySelector("button");
			if (button.classList.contains("push-optionButton")) {
				//optionButtonの中のinputを取得nameに[optionId-]を含むもの
				const option = optionButton.querySelector('input[name^="optionId-"]');
				//選択肢のユニークidを格納
				selectOptions.push(option.value);
			}
		})
		for (let i = 0; i < selectOptions.length; i++) {
			const optionId = document.createElement('input');
			optionId.type = 'hidden';
			optionId.name = 'optionId';
			optionId.value = selectOptions[i];
			submitContainer.appendChild(optionId);
		}
		submitContainer.submit();
	}


});