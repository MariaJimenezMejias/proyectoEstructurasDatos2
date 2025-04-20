import java.util.*;

class ArbolAVL<T extends Comparable<T>> {
    private NodoAVL<T> raiz;

    private static class NodoAVL<T> {
        T dato;
        NodoAVL<T> izquierdo, derecho;
        int altura;

        NodoAVL(T dato) {
            this.dato = dato;
            this.altura = 1;
        }
    }

    private int altura(NodoAVL<T> nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private NodoAVL<T> rotarDerecha(NodoAVL<T> y) {
        NodoAVL<T> x = y.izquierdo;
        NodoAVL<T> T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        return x;
    }

    private NodoAVL<T> rotarIzquierda(NodoAVL<T> x) {
        NodoAVL<T> y = x.derecho;
        NodoAVL<T> T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        return y;
    }

    private NodoAVL<T> balancear(NodoAVL<T> nodo) {
        int balance = altura(nodo.izquierdo) - altura(nodo.derecho);
        if (balance > 1) {
            if (altura(nodo.izquierdo.izquierdo) >= altura(nodo.izquierdo.derecho)) {
                return rotarDerecha(nodo);
            } else {
                nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
                return rotarDerecha(nodo);
            }
        }
        if (balance < -1) {
            if (altura(nodo.derecho.derecho) >= altura(nodo.derecho.izquierdo)) {
                return rotarIzquierda(nodo);
            } else {
                nodo.derecho = rotarDerecha(nodo.derecho);
                return rotarIzquierda(nodo);
            }
        }
        return nodo;
    }

    public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private NodoAVL<T> insertarRec(NodoAVL<T> nodo, T dato) {
        if (nodo == null)
            return new NodoAVL<>(dato);
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        else if (cmp > 0)
            nodo.derecho = insertarRec(nodo.derecho, dato);
        else
            return nodo;

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        return balancear(nodo);
    }

    public void eliminar(T dato) {
        raiz = eliminarRec(raiz, dato);
    }

    private NodoAVL<T> eliminarRec(NodoAVL<T> nodo, T dato) {
        if (nodo == null) return null;

        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, dato);
        } else if (cmp > 0) {
            nodo.derecho = eliminarRec(nodo.derecho, dato);
        } else {
            if (nodo.izquierdo == null || nodo.derecho == null) {
                nodo = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;
            } else {
                NodoAVL<T> sucesor = obtenerMinimo(nodo.derecho);
                nodo.dato = sucesor.dato;
                nodo.derecho = eliminarRec(nodo.derecho, sucesor.dato);
            }
        }

        if (nodo == null) return null;

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        return balancear(nodo);
    }

    private NodoAVL<T> obtenerMinimo(NodoAVL<T> nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    public List<T> obtenerElementosOrdenados() {
        List<T> elementos = new ArrayList<>();
        inOrden(raiz, elementos);
        return elementos;
    }

    private void inOrden(NodoAVL<T> nodo, List<T> elementos) {
        if (nodo != null) {
            inOrden(nodo.izquierdo, elementos);
            elementos.add(nodo.dato);
            inOrden(nodo.derecho, elementos);
        }
    }
}