import java.util.*;

public class ArrayMap<K,V> extends AbstractMap<K,V> {

    private Vector<K> keys = new Vector<>();
    private Vector<V> values = new Vector<>();

    public V put(K key, V value){
        int i =keys.indexOf(key);
        if(i < 0){
            keys.add(key);
            values.add(value);
            return null;
        }else{
            V previousValue = values.get(i);
            values.set(i,value);
            return previousValue;
        }
    }

    public boolean containsKey(Object key){
        int i = keys.indexOf(key);
        if(i < 0){
            return false;
        }
        return true;
    }

    public V get(Object key){
        int i = keys.indexOf(key);
        if(i < 0){
            return null;
        }
        return values.get(i);
    }

    public int size(){
        return keys.size();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> entries = new HashSet<>();

        for(int i = 0; i < this.size(); i++){
            entries.add(new ArrayMapEntry<>(keys.get(i),values.get(i)));
        }

        return entries;
    }

    public class ArrayMapEntry<K,V> implements Entry<K, V> {

        private K key;
        private V value;

        public ArrayMapEntry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V previousValue = this.value;
            this.value = value;
            return previousValue;
        }

        @Override
        public boolean equals(Object o) {
            ArrayMapEntry e1 = this;
            ArrayMapEntry e2 = (ArrayMapEntry)o;
            return (e1.getKey()==null ?
                    e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &&
                    (e1.getValue()==null ?
                            e2.getValue()==null : e1.getValue().equals(e2.getValue()));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(this.getKey());
            sb.append(",");
            sb.append(this.getValue());
            sb.append("]");
            return sb.toString();
        }
    }
}
