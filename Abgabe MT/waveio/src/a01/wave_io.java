package a01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class wave_io {
    public static void main(String[] args) {
        int samples = 0;
        int validBits = 0;
        long sampleRate = 0;
        long numFrames = 0;
        int numChannels = 0;


		String inFilename = "./src/a01/Musik_03.wav";
		String outFilename = "./src/sine_lo06.wav";

        if (args.length < 1) {
            try {
                throw new WavFileException("At least one filename specified  (" + args.length + ")");
            } catch (WavFileException e1) {
                e1.printStackTrace();
            }
        }

        // Samples in dem Array readWavFile.sound

        inFilename = args[0];

        // Implementierung bei einem Eingabeparameter

        WavFile readWavFile = null;
        try {
            readWavFile = WavFile.read_wav(inFilename);

            // headerangaben
            numFrames = readWavFile.getNumFrames();
            numChannels = readWavFile.getNumChannels();
            samples = (int) numFrames*numChannels;
            validBits = readWavFile.getValidBits();
            sampleRate = readWavFile.getSampleRate();

            //Übung 1
            // 2a Samples schreiben
			//String high = "sine_hi02.txt";
			//String low = "sine_lo02.txt";
 			//PrintWriter out = new PrintWriter(new PrintWriter(new File(low)), true);

		/*	for (int i=0; i < samples;i++) {
				out.println(readWavFile.sound[i]);

			} 
			out.close(); */

		    if (args.length == 1)
				System.exit(0); 

		    //src/1Nature.bmp src/output.bmp
            //Übung 5
            //1.a
            int db = 15;
            double f = Math.pow(10, (db/20f));
            double fSound;
            for (int i = 0; i < samples; i++) {
                fSound = readWavFile.sound[i];
                fSound *= f;

                if (fSound > Short.MAX_VALUE) {
                	fSound = Short.MAX_VALUE;
				}
                if (fSound < Short.MIN_VALUE){
                	fSound = Short.MIN_VALUE;
				}

                readWavFile.sound[i] = (short) fSound;
            }


            //2.a
     /*       double delayInMillis = 10;
            double a = 0.6;
            int n = (int) (delayInMillis/1000*sampleRate*numChannels);
            for (int i = 0; i < samples; i++) {
                if (n < i) {
                    readWavFile.sound[i] = (short) (0.5*readWavFile.sound[i] + 0.5*a*readWavFile.sound[i - n]);
                }
            }*/


            //3.a
/*            boolean tief = true;
            if (tief) {
                for (int i = 1; i < samples; i++) {
                    readWavFile.sound[i] = (short) (0.5*readWavFile.sound[i] + 0.45*readWavFile.sound[i - 1]);
                }
            } else {
                for (int i = 1; i < samples; i++) {
                    readWavFile.sound[i] = (short) (0.5*readWavFile.sound[i] - 0.45*readWavFile.sound[i - 1]);
                }
            }*/


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (WavFileException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        // Implementierung bei Ein-und Ausgabeparameter

        outFilename = args[1];
        try {

            // Übung 1
            // 2e Downsampling
/*			numFrames /= 2;
			sampleRate /= 2;

			for (int i=0; i < samples / 2; i++) {
				readWavFile.sound[i] = readWavFile.sound[i*2];
			}

            // 3b Bitreduzierung
			int reduced_bits = 1;
			for (int i=0; i < samples; i++) {
				readWavFile.sound[i] /= Math.pow(2,reduced_bits);
				readWavFile.sound[i] *= Math.pow(2, reduced_bits);
			}

            // 3e Bitreduzierung Differenz bzw Quantisieren
			//int reduced_bits = 8;
			short raw[] = new short[samples];
			for (int i=0; i < samples; i++) {
				raw[i] = readWavFile.sound[i];
				readWavFile.sound[i] /= Math.pow(2,reduced_bits);
				readWavFile.sound[i] *= Math.pow(2, reduced_bits);
				readWavFile.sound[i] -= raw[i];
				readWavFile.sound[i] *= Math.pow(2, 16 - reduced_bits - 1);
			}*/

            WavFile.write_wav(outFilename, numChannels, numFrames, validBits, sampleRate, readWavFile.sound);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
