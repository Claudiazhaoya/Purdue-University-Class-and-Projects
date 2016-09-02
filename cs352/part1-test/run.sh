wget https://raw.githubusercontent.com/mhoc/cs352-test-cases/master/maker.py
wget https://github.com/mhoc/cs352-test-cases/releases/download/1.0/test-cases-1.0.tar.gz 
tar -xf test-cases-1.0.tar.gz 
rm test-cases-1.0.tar.gz 
python maker.py test 
rm maker.py 
rm -r test
rm run.sh
