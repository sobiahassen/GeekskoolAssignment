;; The mapper function which recursively creates a map of entries 

;; Here I am using an iterative process in a recursive procedure, The state of the process can be summarized by the 
;; fixed number of state variables (accumulator & input ), and the rule how to move from one state to another.


;; Vector --> Map  [type signature]

(defn mapper  
 ([input]
   (mapper input {}))
 ([input accumulator]
   (if (empty? input)
       accumulator
      (recur (rest input) 
             (let [intermediate (clojure.string/split (first input) #":x:")] ;; using recur as clojure does not 
                 (assoc accumulator                                         ;; privide tail call optimization
                       (keyword (first intermediate))
                       (dissoc (zipmap [:u-id :g-id :ignore :home-dir :shell]
                                       (clojure.string/split (second intermediate ) #":"))
                      :ignore)))))))
                              

(mapper (clojure.string/split-lines (slurp "passwd.txt")))  ;; size if file "passwd.txt" is small we can keep it 
                                                            ;; in the memory therefore using (slurp)




