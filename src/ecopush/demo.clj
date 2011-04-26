;;; demo file for testing/debugging
(ns ecopush.demo
  [use [ecopush core push]])

;;; push-strat demos

(push-strat
 (random-code 10 @registered-instructions) ; randomly generate push code 
 (vec (repeatedly 10 #(rand-int 2)))	; random player-decisions   
 (vec (repeatedly 10 #(rand-int 2))))	; random all-decisions

;;; look at the auxiliary stack 
(:auxiliary
 (run-push 
  (random-code 10 @registered-instructions)
  (->>
   (make-push-state)
   (push-item (vec (repeatedly 10 #(rand-int 2))) :auxiliary) ; player-decisions
   (push-item (vec (repeatedly 10 #(rand-int 2))) :auxiliary) ; all-decisions			  
   (push-item 1 :integer)
   (push-item 0 :integer))))

;;; look at top item of auxiliary stack 
(top-item :auxiliary
	  (run-push 
	   (random-code 10 @registered-instructions)
	   (->>
	    (make-push-state)
	    (push-item (vec (repeatedly 10 #(rand-int 2))) :auxiliary) ; player-decisions
	    (push-item (vec (repeatedly 10 #(rand-int 2))) :auxiliary) ; all-decisions			  
	    (push-item 1 :integer)
	    (push-item 0 :integer))))

;;; create-players demos

;;; create player list all with random push stragies
(create-players 7 3 (repeatedly 7 #(Strategy. (random-code 10 @registered-instructions) "push")))

;;; create player list with mix of clojure and push strategies
(create-players 4 3 (cons (Strategy. (quote (rand-int 2)) "clj")
			  (repeatedly 3 #(Strategy. (random-code 10 @registered-instructions) "push"))))

