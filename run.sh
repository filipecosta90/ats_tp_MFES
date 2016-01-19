#/bin/bash
EXAMPLE_DIR=exemplos/
<<<<<<< HEAD
EXAMPLE_FILE=convertASCII.i
=======
EXAMPLE_FILE=mult2num.i
>>>>>>> dd6a867dbf158694eb641c906854faa2e7b493bc
RES_FILE=res.msp
make clean
make all
cp $EXAMPLE_DIR$EXAMPLE_FILE genI
cd genI
javac gram/Main.java
echo "ended compiling"
java gram/Main < $EXAMPLE_FILE > $RES_FILE
echo "ended running java gram/Main"
cp $RES_FILE ../genMaqV
cd ../genMaqV
javac maqv/Main.java
echo "ended compiling maqv/Main"
echo "maqvMain:\n"
java maqv/Main $RES_FILE
cd ../genI
open metrics.txt
