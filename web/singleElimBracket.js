const form = document.querySelector("#numberOfPlayersForm");
const input = document.querySelector("#numberOfPlayers");
const main = document.querySelector('main');

form.addEventListener("submit", (event) => {
    event.preventDefault();
    console.log(input.value);
    printBracket(createBracket(input.value));
})
/*
    This function creates a method that returns an nested array of Peters that helps create the structure for printing the bracket
*/
function createBracket(num) {
    if (num < 2) {
        return;
    }
    const myArray = [];
    let x = 2;
    while (x < num) {
        const toAdd = [];
        for (let i = 1; i <= x; i++ ){
            toAdd.push("Peter " + i);
        }
        myArray.unshift(toAdd);
        x *= 2;
    }

    const toAdd = [];
    for (let i = 1; i <= num; i++ ){
        toAdd.push("Peter " + i);
    }
    while (toAdd.length < x) {
        toAdd.push("null");
    }

    myArray.unshift(toAdd);

    return myArray;
}

/*
    adds html elements to display the tournament
*/
function printBracket(arrOfPlayers) {
    main.innerHTML = '';

    for(let round = 1; round <= arrOfPlayers.length; round++) {
        const ul = document.createElement('ul'); 
        ul.className = "round"; 
        const li = document.createElement('li');
        li.className = "spacer";
        li.innerHTML = "&nbsp;";
        ul.appendChild(li);

        const players = arrOfPlayers[round-1];

        for(let i = 0; i < players.length;i++){
            if(i % 2 === 0) {
                const li1 = document.createElement('li');
                li1.className = "game game-top";
                li1.innerHTML = players[i];

                const li2 = document.createElement('li');
                li2.className = "game game-spacer";
                li2.innerHTML = "&nbsp;";

                ul.appendChild(li1);
                ul.appendChild(li2);
            } else {
                const li1 = document.createElement('li');
                li1.className = "game game-bottom";
                li1.innerHTML = players[i];

                const li2 = document.createElement('li');
                li2.className = "spacer";
                li2.innerHTML = "&nbsp;";

                ul.appendChild(li1);
                ul.appendChild(li2);
            }
        }

        main.appendChild(ul);
    }
}

