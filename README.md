# mathematics-Library

Библиотека для работы с линейной алгеброй. Есть пожелания - пишите)

Так, ну по базе. Данная библиотека, вроде как, сделана по принципам ООП. Чего ей/мне не хватает для полного счастья? Пойдем по порядку:
- увы, но нет возможности называть методы одинаково
- Нет возможности не делать громоздких операций, по типу сейчас, да и потом, это реализовано так:
```  
Vector3f first = new Vector3f(1,2,3);  
Vector3f second = new Vector3f(2,3,4);  
// я хочу сложить 2 вектора  
res = first.add(second);  
// как бы отстой. я хочу так:  
res = first + second;  
// но наша любимая java не хочет давать мне право переопределять арифмитические операторы  👍  
```  
- К сожалению. я передумал ее делать закрытой и хешировать внутренности :( (нет, не то, чтобы мне стало впадлу понимать, как это делается, скорее я понял, что смысла особо нет - так особо ни у кого не будет интереса чекать мою библиотеку, хах)
- тесты - пока прописаны не все. в будущем пропишу еще, наверное.
- Из-за 3го пункта возможны баги) Буду благодарен - если что-то найдете.
- Если вы поймете как уйти от `createInstance` и `instantiateVector` - я буду счастлив, напишите мне :)
# Краткий функционал

Библиотека состоит из 2х главных классов, в которых прописана основная логика для матричных преобразований - `AbstractMatrix` и `AbstractVector`. От этих классов унаследовано все остальное.
## **ВАЖНО**

Если вы хотите добавить что-то свое - по типу, `Vector5f`  - то вам необходимо в данном классе указать длину вашего вектора, т.е.

`private static final int SIZE = 5;`


То же самое касается матриц!

Я пока еще не придумал, как автоматизировать все это дерьмо - чтобы SIZE как-то сам прописывался... ладно, потом подумаю (нет). Опять же, если знаешь как - напиши)
## Основные операции
### `AbstractVector`
#### Конструкторы
    Вы можете спросить, зачем ты выдлелил это в отделльный пункт, однако это мой проект, так что, МОГУ СЕБЕ ПОЗВОЛИТЬ :)   
Если серьезно - в данный момент по дефолту прописаны след. Конструкторы:
```  
public AbstractVector(float... components) {}  
public AbstractVector() {} // есть шанс, что в будущем я удалю данный метод :)  
```  

В чем прелесть и почему я рекомендую вам пользоваться именно моими классами, т.е. для `Vector5f` делать так:
```  
public Vector5f(float... components) {  
super(components);  
}  
public Vector5f() {  
super();  
}  
```  
Таким образом вы получите возможность задавать вектор сразу несколькими способами:
```  
Vector3f first = new Vector3f(1,2,3);  
int[] a = {1,2,3}  
Vector3f second = new Vector3f(a);  
```  
Тема классная - рекомендую)  
`AbstractVector()` - создает нулевой вектор.
#### Сложение
