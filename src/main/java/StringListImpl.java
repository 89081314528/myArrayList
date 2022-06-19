import ru.julia.exception.CapacityException;
import ru.julia.exception.ElementIsAbsentException;
import ru.julia.exception.OtherArrayIsNullException;
import ru.julia.exception.SizeException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private int capacity;
    private String[] array;
    private int size;

    @Override
    public String[] getArray() {
        return array;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "array=" + Arrays.toString(array);
    }

    public StringListImpl(int capacity) {
        this.capacity = capacity;
        array = new String[capacity];
        this.size = 0;
    }

    @Override
    //добавляет элемент в конец листа
    //при превышении емкости массив расширяется
    //
    public String add(String item) {
        if (size < capacity) {
            array[size] = item;
        } else {
            capacity = capacity * 2;
            String[] newArray = new String[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            newArray[size] = item;
            array = newArray;
        }
        size = size + 1;
        return item;
    }

    @Override
    //добавляет элемент на определенную позицию, сдвигая оставшиеся элементы вправо
    //
    public String add(int index, String item) {
        if (index > size) {
            throw new SizeException();
        }
        if ((size + 1) > capacity) {
            capacity = capacity * 2;
            String[] newArray = new String[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        if (index == size) {
            array[index] = item;
        }
        if (index < size) {
            String current = array[index];
            String next = array[index + 1];
            String actual = item;
            array[index] = actual;
            for (int i = index + 1; i <= size; i++) {
                actual = current;
                current = array[i];
                next = array[i + 1];
                array[i] = actual;
            }
        }
        size = size + 1;
        return item;
    }

    @Override
    //добавляет элемент на определенную позицию, затирая существующий
    public String set(int index, String item) {
        if (index > capacity - 1) {
            throw new CapacityException();
        }
        if (index > size - 1) {
            throw new SizeException();
        }
        array[index] = item;
        return item;
    }

    @Override
    // Удаление элемента.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    //
    public String remove(String item) {
        boolean itemIsAbsent = true;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                index = i;
                itemIsAbsent = false;
                break;
            }
        }
        if (itemIsAbsent) {
            throw new ElementIsAbsentException();
        }
        for (int j = index; j < size - 1; j++) {
            if (index < size - 1) {
                array[j] = array[j + 1];
            }
        }
        array[size - 1] = null;
        size = size - 1;
        return item;
    }

    @Override
    // Удаление элемента по индексу.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    public String remove(int index) {
        if (index >= size) {
            throw new ElementIsAbsentException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size = size - 1;
        return array[index];
    }

    @Override
    // Проверка на существование элемента. Вернуть true/false;
    //
    public boolean contains(String item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    // Поиск элемента.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    //
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    // Поиск элемента с конца.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    //
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    // Получить элемент по индексу.
    // Вернуть элемент или исключение, если выходит за рамки фактического количества элементов.
    public String get(int index) {
        if (index >= size) {
            throw new ElementIsAbsentException();
        }
        return array[index];
    }

    @Override
    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение, если передан null.
    //
    public boolean equals(StringListImpl otherList) {
        if (otherList == null) {
            throw new OtherArrayIsNullException();
        }
        int count = 0;
        if (size == otherList.size()) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(otherList.get(i))) {
                    count = count + 1;
                }
            }
            if (count == size) {
                return true;
            }
        }
        return false;
    }

    @Override
    // Вернуть фактическое количество элементов
    public int size() {
        return size;
    }

    @Override
    // Вернуть true, если элементов в списке нет, иначе false.
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    // Удалить все элементы из списка.
    public void clear() {
        size = 0;
    }

    @Override
    // Создать новый массив из строк в списке и вернуть его.
    public String[] toArray() {
        String[] newArray = new String[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}
