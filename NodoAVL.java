class NodoAVL<T extends Comparable<T>> {
    T dato;
    int altura;
    NodoAVL<T> izquierdo, derecho;

    public NodoAVL(T dato) {
        this.dato = dato;
        this.altura = 1;
    }
}