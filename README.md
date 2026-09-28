
# Решение задания №3 из технического задания 
Решена Задача 3 (продвинутый уровень), реализованы quicksort и mergesort

# ListSorter

Утилитарный класс для сортировки списков (`java.util.List`) двумя алгоритмами:
быстрой сортировкой (quicksort) и сортировкой слиянием (mergesort).


## Возможности

- Быстрая сортировка (quicksort) — in-place, со случайным выбором опорного элемента.
- Сортировка слиянием (mergesort) — устойчивая, с использованием временного буфера.
- Два варианта вызова каждого алгоритма:
    - по естественному порядку (`Comparable`);
    - с пользовательским компаратором (`Comparator<? super T>`).
- Обобщённые методы, работающие с любым типом `T`.

## Требования

- **Java 26**
- Система сборки: Maven

## Структура проекта

```
src/
├── main/
│   └── java/
│       └── org/example/
│           └── ListSorter.java
└── test/
    └── java/
        └── org/example/
            └── ListSorterTest.java
```
## API

### Сортировка по естественному порядку

```java
public static <T extends Comparable<? super T>> List<T> quickSort(List<T> list)
public static <T extends Comparable<? super T>> List<T> mergeSort(List<T> list)
```

Требует, чтобы элементы реализовывали `Comparable` (сам тип `T` или его предок).

### Сортировка с компаратором

```java
public static <T> List<T> quickSort(List<T> list, Comparator<? super T> comparator)
public static <T> List<T> mergeSort(List<T> list, Comparator<? super T> comparator)
```

Позволяет задать произвольный порядок сравнения.

## Пример использования

```java
List<Integer> numbers = new ArrayList<>(List.of(5, 2, 9, 1, 7));

// По естественному порядку
ListSorter.quickSort(numbers);
System.out.println(numbers); // [1, 2, 5, 7, 9]

// С компаратором (обратный порядок)
List<String> words = new ArrayList<>(List.of("banana", "apple", "cherry"));
ListSorter.mergeSort(words, Comparator.reverseOrder());
System.out.println(words); // [cherry, banana, apple]
```

## Сложность

| Алгоритм     | Время (среднее) | Время (худшее) | Память     |
|--------------|-----------------|----------------|------------|
| QuickSort    | O(n log n)      | O(n²)          | O(log n)   |
| MergeSort    | O(n log n)      | O(n log n)     | O(n)       |

Примечания:
- QuickSort использует случайный выбор опорного элемента, что снижает
  вероятность худшего случая на почти отсортированных данных.
- MergeSort устойчив: сохраняет относительный порядок равных элементов.

## Особенности реализации

- Оба алгоритма работают **in-place** по отношению к исходному списку:
  результат записывается в тот же объект `List` возвращает его.
- Пустой список или список из одного элемента не изменяется.
- `null` в качестве списка игнорируется (метод ничего не делает).
- Оба метода возвращают тот же список.
- QuickSort использует схему разбиения с двумя указателями,
  возвращая границы `{i, j}` для рекурсивных вызовов.
- MergeSort использует один временный список на весь процесс сортировки,
  а не создаёт новый на каждом уровне рекурсии.

## Ограничения

- Работает только с `List`, у которого есть доступ по индексу за O(1)
  (`ArrayList`, `Vector`). Для `LinkedList` производительность будет ниже
  ожидаемой из-за `get`/`set` за O(n).

## Тестирование

### Зависимости

| Зависимость | Назначение            |
|-------------|-----------------------|
| JUnit 5 | Unit-тесты            |
| Maven Surefire Plugin | Отчет по тестированию |
| PIT (pitest) | Mutation-тестирование |

### Отчёты

- [Surefire — результаты тестов](https://artem-rodionov.github.io/Axenix-test-intern/surefire/surefire.html)
- [PIT — мутационное тестирование](https://artem-rodionov.github.io/Axenix-test-intern/pit/index.html)


Запуск:

```
mvn clean test
```