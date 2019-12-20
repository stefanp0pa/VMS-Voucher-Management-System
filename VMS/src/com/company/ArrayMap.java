package com.company;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ArrayMap<K,V> extends AbstractMap<K,V> {

    public V put(K key, V value){
        return super.put(key,value);
    }

    public boolean containsKey(Object key){
        return super.containsKey(key);
    }

    public V get(Object key){
        return super.get(key);
    }

    public int size(){
        return super.size();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<K> keySet= super.keySet();
        if(keySet==null)
            return null;

        Set<Entry<K,V>> entries = null;

        K[] keysArray = (K[])keySet.toArray();
        int keysSize = keysArray.length;
        for(int i = 0; i < keysSize; i++){
            V val = this.get(keysArray[i]);
            entries.add(new ArrayMapEntry<K,V>(keysArray[i],val));
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
            V prevValue = this.value;
            this.value = value;
            return prevValue;
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
            return null;
        }
    }
}
