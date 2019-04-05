Steps to reproduce:
- just run `clj -m cljs.main --optimizations advanced -c reproduce-invalid-js.core`

Compilation will fail with
```
Exception in thread "main" clojure.lang.ExceptionInfo: failed compiling file:/Users/***/projects/reproduce-invalid-js/src/reproduce_invalid_js/core.cljs {:file #object[java.io.File 0x53c6f96d "/Users/***/projects/reproduce-invalid-js/src/reproduce_invalid_js/core.cljs"], :clojure.error/phase :compilation}
```

File `out/reproduce_invalid_js/core.js` will contain invalid js:
```js
// ...
if(cljs.core.truth_(reproduce_invalid_js.core.init_data)){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$3(reproduce_invalid_js.core.state,cljs.core.merge
// end of file
```

How to fix:
- wrap `init-data` into `(some? init-data)` before passing to `when`:
```clojure
(defn render-app []
  (when (some? init-data)
    (swap! state merge init-data)))
```
- change `org.clojure/clojurescript` version to `1.10.439`