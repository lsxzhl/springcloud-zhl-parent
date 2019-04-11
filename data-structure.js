function Stack() {
    //使用数据存储数据
    var items = [];

    //放入羽毛球
    this.push = function (item) {
        items.push(item);
    };

    //拿出羽毛球
    this.pop = function () {
        return items.pop();
    };
    //数组的pop方法会删除最靠后的那个元素，同时return该元素

    //top方法返回栈顶元素
    this.top = function () {
     return items[items.length - 1];
    };

    // isEmpty返回栈是否为空
    this.isEmpty = function () {
        return items.length == 0;
    }

    //size方法返回栈的大小
    this.size = function () {
        return items.length;
    }

    //clear 清空栈
    this.clear =  function () {
        items = []
    }


}


function is_leagl_brackets(string) {
    var stack = new Stack();
    for(var i=0; i<string.length; i++){
        var item = string[i];
        if(item == "("){
            //将左括号压入栈
            stack.push(item);
        }else if (item == ")"){
            //如果为空，就说明没有左括号与之抵消
            if(stack.isEmpty()){
                return false;
            }else {
                //将栈顶的元素弹出
                stack.pop();
            }
        }
    }
    return stack.size() == 0;
}

console.log(is_leagl_brackets("()()))"));
