import ru.julia.exception.CapacityException;
import ru.julia.exception.ElementIsAbsentException;
import ru.julia.exception.OtherArrayIsNullException;
import ru.julia.exception.SizeException;

import java.util.Arrays;

public class IntListImpl implements IntList {

    private int capacity;
    private int[] array;
    private int size;

    @Override
    public int[] getArray() {
        return array;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "array=" + Arrays.toString(array);
    }

    public IntListImpl(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        this.size = 0;
    }

    @Override
    //добавляет элемент в конец листа
    //при превышении емкости массив расширяется
    public int add(int item) {
        if (size < capacity) {
            array[size] = item;
        } else {
            capacity = capacity * 2;
            int[] newArray = new int[capacity];
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
    public int add(int index, int item) {
        if (index > size) {
            throw new SizeException();
        }
        if ((size + 1) > capacity) {
            capacity = capacity * 2;
            int[] newArray = new int[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        if (index == size) {
            array[index] = item;
        }
        if (index < size) {
            int current = array[index];
            int next = array[index + 1];
            int actual = item;
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
    public int set(int index, int item) {
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
    public int remove(int item) {
        boolean itemIsAbsent = true;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == item) {
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
        array[size - 1] = 0;
        size = size - 1;
        return item;
    }

    @Override
    // Удаление элемента по индексу.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    public int removeByIndex(int index) {
        if (index >= size) {
            throw new ElementIsAbsentException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = 0;
        size = size - 1;
        return array[index];
    }

    @Override
    // Проверка на существование элемента. Вернуть true/false;
    public boolean contains(int item) {
        sortInsertion();
        return binarySearch(item);
   }

    @Override
    // Поиск элемента.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    public int indexOf(int item) {
        for (int i = 0; i < size; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    // Поиск элемента с конца.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    public int lastIndexOf(int item) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    // Получить элемент по индексу.
    // Вернуть элемент или исключение, если выходит за рамки фактического количества элементов.
    public int get(int index) {
        if (index >= size) {
            throw new ElementIsAbsentException();
        }
        return array[index];
    }

    @Override
    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение, если передан null.
    public boolean equals(IntListImpl otherList) {
        if (otherList == null) {
            throw new OtherArrayIsNullException();
        }
        int count = 0;
        if (size == otherList.size()) {
            for (int i = 0; i < size; i++) {
                if (array[i] == otherList.get(i)) {
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
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }
        size = 0;
    }

    @Override
    // Создать новый массив из строк в списке и вернуть его.
    public int[] toArray() {
        int[] newArray = new int[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    // Добавить в реализацию приватный метод с самой быстрой из рассмотренных сортировок.
    //??? приватный можно использовать только в классе IntListImpl?
    private void sortInsertion() {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    // Добавить в реализацию приватный метод бинарного поиска.
    // метод contains переработать, осуществив сортировку вставками и вызвав метод бинарного поиска.
    private boolean binarySearch(int item) {
        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == array[mid]) {
                return true;
            }

            if (item < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
