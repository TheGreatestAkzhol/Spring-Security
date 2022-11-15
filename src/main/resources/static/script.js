var Parent = document.querySelector('.js-parent');
function March(){
    for(var i = 1; i<32; i++){
        var day = document.createElement('div');
        day.innerHTML = i;
        day.classList.add('element');
        Parent.appendChild(day);
    };
}
March();
const dragAndDrop = () => {
    const cards = document.querySelectorAll('p');
    const cells = document.querySelectorAll('.element');
    const dragStart = function(){
        setTimeout(() => {
            this.classList.add('hide');
        },0)
    };
    const dragEnd = function(){
        this.classList.remove('hide');
    };
    const dragOver = function(evt){
        evt.preventDefault();
        console.log('over');
    };
    const dragEnter = function(evt){
        this.classList.add('hovered');
        evt.preventDefault();
        console.log('enter');
    };
    const dragLeave = function(){
        this.classList.remove('hovered');
        console.log('leave');
    };
    const dragDrop = function(){
        this.classList.add('lightGreen');
        this.classList.remove('hovered');
        console.log('drop');
    };
    cells.forEach((cell) => {
        cell.addEventListener('dragover',dragOver);
        cell.addEventListener('dragenter',dragEnter);
        cell.addEventListener('dragleave',dragLeave);
        cell.addEventListener('drop',dragDrop);
    });
    cards.forEach((card) => {
        card.addEventListener('dragstart',dragStart);
        card.addEventListener('dragend',dragEnd);

    });
}
dragAndDrop();