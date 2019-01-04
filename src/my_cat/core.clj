(ns my-cat.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn get-stream
  "入力側のストリームを与えられた引数に基づいて返す"
  [args]
  "引数が空かどうか判定する"
  (if (empty? args)
    ; コマンドライン引数がない場合は標準入力を返す
  　　*in*
    ; コマンドライン引数からファイルパスを取得
  　　(io/file (first args))))

(defn print-contents
  "与えられた文字列のシーケンスを標準出力する"
  [contents]
  ; シーケンスの要素を一つずつ取り出して処理する
  (doseq [i contents]
    ; その要素を表示する
    (println i)))

(defn -main
  "コマンドライン引数で与えられたファイル、または、標準入力で与えられた文字列を表示する"
  [& args]
  (let [
    ; 入力ストリームを取得
    stream (get-stream args)
    ; 取得したストリームを行のシーケンスに展開
    contents (line-seq (io/reader stream))]
    ; 各行を順番に表示する
    (print-contents contents)))
