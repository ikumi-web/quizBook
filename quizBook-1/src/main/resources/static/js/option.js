/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	const optionsContainer = document.getElementById('optionsContainer');
	const addOptionButton = document.getElementById('addOptionButton');
	const sortOptionButton = document.getElementById('sortOptionButton');
	const preRemoveButton = document.querySelectorAll('.preRemoveButton');
	
	

	// 次に使用する選択肢番号
	let optionCounter = 0;

	// 利用可能な番号を管理するセット
	const availableNumbers = new Set();

	// 利用可能な最小の番号を取得する関数
	function getNextOptionNumber() {
		if (availableNumbers.size > 0) {
			// セットを配列に変換してソートし、最小の番号を取得
			const sortedAvailable = Array.from(availableNumbers).sort((a, b) => a - b);
			const number = sortedAvailable[0];
			availableNumbers.delete(number);
			return number;
		} else {
			//前置インクリメントじゃないとダメ
			return ++optionCounter;
		}
	}

	// 選択肢を追加する関数
	function addOption() {
		
		const divElements = optionsContainer.querySelectorAll('div');
		optionCounter = divElements.length;
		
		const optionNumber = getNextOptionNumber();

		const optionDiv = document.createElement('div');
		optionDiv.classList.add('option');
		optionDiv.id = `option-${optionNumber}`;

		const optionLabel = document.createElement('label');
		optionLabel.textContent = `選択肢 ${optionNumber}: `;

		const optionInput = document.createElement('input');
		optionInput.type = 'text';
		optionInput.name = `options[${optionNumber-1}].optionText`
		optionInput.placeholder = '選択肢を入力';

		const removeButton = document.createElement('button');
		removeButton.textContent = '削除';
		removeButton.type = 'button';
		removeButton.classList.add('removeButton');
		removeButton.addEventListener('click', () => removeOption(optionDiv.id, optionNumber));
		
		const checkBox = document.createElement('input');
		checkBox.type = 'checkbox';
		checkBox.name = `options[${optionNumber-1}].correct`;

		optionDiv.appendChild(optionLabel);
		optionDiv.appendChild(optionInput);
		optionDiv.appendChild(removeButton);
		optionDiv.appendChild(checkBox);

		optionsContainer.appendChild(optionDiv);
	}

	// 選択肢を削除する関数
	function removeOption(optionId, optionNumber) {
		const optionToRemove = document.getElementById(optionId);
		if (optionToRemove) {
			optionsContainer.removeChild(optionToRemove);
			// 削除された番号を利用可能にする
			availableNumbers.add(optionNumber);
		}
	}
	
	function preRemoveOption(event) {
		//一番近い親要素のクラスを取得している。
		const optionToRemove = event.target.closest('.option');
        if (optionToRemove) {
            optionToRemove.remove();
			//親クラスからidを取り出して文字列と数字に分けて直近で
			//削除された選択肢番号を渡している。
			const divId = optionToRemove.id;
			const optionNumber = parseInt(divId.split('-')[1]);
			// 削除された番号を利用可能にする
			availableNumbers.add(optionNumber);
		}
	}
		
	function sortOption(){
        const items = Array.from(optionsContainer.children);

        items.sort((elementA, elementB) => {
			//option-1を二つに分けて[option][1]その配列の2番目を取り出してInt型にして格納
			//お互いを引いて比較し昇順にソート
            const elementIdA = parseInt(elementA.id.split('-')[1]);
            const elementIdB = parseInt(elementB.id.split('-')[1]);
            return elementIdA - elementIdB;
        });
        //子要素をソートされた順にコンテナに格納していく。
        items.forEach(item =>{optionsContainer.appendChild(item)});
    }
    
    //もともとある選択肢の削除ボタンクラスを全て取り出して、その全てのボタンに
    //preRemoveOptionを付与している。
    preRemoveButton.forEach(button=>{button.addEventListener('click',preRemoveOption)});
    
    
    // 「選択肢を昇順に整理」ボタンがクリックされた時に選択肢を昇順にソート
    sortOptionButton.addEventListener('click',sortOption);

	// 「選択肢を追加」ボタンがクリックされたときに新しい選択肢を追加
	addOptionButton.addEventListener('click', addOption);
	
	
	
	
	
});