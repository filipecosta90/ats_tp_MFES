#/bin/bash
EXAMPLE_DIR=exemplos/
EXAMPLE_FILE=maiorDeDoisNumeros.i
RES_FILE=res.msp
make clean
make all
cp $EXAMPLE_DIR$EXAMPLE_FILE genI
cp gram/standards_c.txt genI
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
#java maqv/Main $RES_FILE
cd ../genI
open metrics.txt
