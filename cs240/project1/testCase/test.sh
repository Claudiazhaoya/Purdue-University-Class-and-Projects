make
./order_book -i gen_1000.txt -o gen_1000O.txt
./orderbook -i gen_1000.txt -o gen_1000Ot.txt
diff gen_1000Ot.txt gen_1000O.txt > result.txt
