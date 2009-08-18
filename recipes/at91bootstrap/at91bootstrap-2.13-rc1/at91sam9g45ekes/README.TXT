AT91SAM9G45-ES ROM Code Replacement

Procedure to install AT91SAM9G45-ES ROM code replacement:
Prior to any operation, the user needs to install AT91-ISP.exe 1.13 and unzip
AT91SAM9G45_RomCode_Replacement.zip file that contains a version of ROM binary code
with USB issue fixed. 

Once done the following steps are necessary to store this version of ROM code in DataFlash.
1. unplug power supply
2. unplug usb device cable
3. remove jumper JP10 (NandFlash Chip Select) & JP12 (DataFlash Chip Select)
4. plug serial cable
5. lauch hyper terminal (115200 bauds, 8 bits, parity none, 1 stop bit, no flow control)
6. plug-in power supply
7. type on hyper terminal : "Alt-0128 Alt-0128 #"
   ALT-0128 is the '€' (Euro) sign for people lucky enough to 
   have a European Keyboard.
   Otherwise you press the ALT button, and then press 0128 in a sequence before releasing the ALT button
   V#
8. AT91SAM9G45-EKES returns ">"
9. close hyper terminal
10. close jumper JP12 (DataFlash® Chip Select)
11. launch SAM-BA (Choose right COM port and AT91SAM9G45-EKES)
12. choose DataFlash media tab in the SAM-BA GUI interface
13. initialize DataFlash, choosing the Enable action in the Scripts rolling menu and press Execute
14. choose Send boot file, press Execute
15. select AT91SAM9G45_RomCode_Replacement.bin binary file and press Open; the media is written down
16. close SAM-BA

On Reset, the faulty ROM code will copy the fixed ROM code in SRAM and launch it. The connexion
through USB to the SAM-BA monitor will work.
Note: Boot from DataFlash media will no longer be available for other purpose.

