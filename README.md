# КПО ИДЗ 2
___

### Условие:
Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки. 
В других папках также могут находиться текстовые файлы и папки (уровень вложенности может оказаться любым).
В каждом файле может быть ни одной, одна или несколько директив формата: 

require ‘<путь к другому файлу от корневого каталога>’

Директива означает, что текущий файл зависит от другого указанного файла.
Необходимо выявить все зависимости между файлами, построить сортированный список, для которого выполняется условие: 
если файл А, зависит от файла В, то файл А находится ниже файла В в списке.
Осуществить конкатенацию файлов в соответствии со списком. Если такой список построить невозможно 
(существует циклическая зависимость), программа должна вывести соответствующее сообщение.

---

### Решение

В моем решении присутствует два типа сортировки. Чтобы их протестировать, нужно 
воспользоваться консольным интерфесом.

Хочу заметить, что для формата кавычек require использовались символы как в ТЗ. 
Символы: ‘ и ’. Прошу заметить это при тестировании программы.
Также программа работает с тектовыми файлами .txt расширения.

Также мною была написана документация к задаче.

По выполнению программы:
В задаче не было написано про то, должна ли программа работать циклически, поэтому
циклическую работу я не реализовал, т.е. при ошибке программа обработает данную ошибку
и завершит работу без повторного запроса данных.

Повторный запрос данных возможен только в начале работы программы.

Спасибо за проверку!

---

### Кот
![img](haha/4040136.jpg)