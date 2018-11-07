# CSCE4013/5013 Homework 2 (Programming)

```
Due date: September 14 , 2018
```
```
Full Grade: 100 pts
```
## I. Task Description

In this assignment, you will implement encrypted communications between two parties, Alice
and Bob, and evaluate the performance of AES and RSA under different parameters. For
simplicity, Alice and Bob will be simulated by two programs running on the same computer.
When Alice sends a message to Bob, she writes the message to a file. Bob receives the message
through reading from the file.

**Part 1:** Implement encryption and decryption using AES with 192-bit key. Assume that Alice
and Bob already have a shared secret key _k_ (e.g., they can read the key from the same file). Alice
encrypts an 18 - byte message _m_ (the message is manually input from command line), and writes
the ciphetext into a file named _ctext_. Bob reads the ciphertext from the file, decrypts it, and prints
the message _m_. The encryption should use the CBC mode.

**Part 2 :** Implement encryption and decryption using RSA with 2048-bit key. Assume that Alice
already has got Bob’s public key (you need to figure out a way to do this). Alice encrypts an 18-
byte message _m_ (the message is manually input from command line) using Bob’s public key, and
writes the ciphetext into a file named _ctext_. Bob reads the ciphertext from the file, decrypts it,
and prints the message _m_.

**Part 3 :** Measure the performance of AES under different parameters. Take a 7-byte message
manually input from command line. Implement AES with 128-bit, 192-bit, and 256-bit keys. For
each key size, run the encryption over the 7-byte message and decryption of its ciphertext for one
thousand times, measure the average time needed for one encryption, and measure the average
time needed for one decryption. Implement RSA with 1024-bit, 2048-bit, and 4096-bit keys. For
each key size, run the encryption over the 7-byte message and decryption of its ciphertext for one
thousand times, measure the average time needed for one encryption, and measure the average
time needed for one decryption. Print the average time of encryption and decryption for each key
size for AES and RSA.

## II. Tests

You need to demo your program to the instructor. A demo sign-up sheet will be distributed in
class later. During the demo, your program will be tested in the following ways.

**Test of Part 1:** For the encryption function, your program needs to take a manually input
plaintext message from the command line, and print the derived ciphertext.
For the decryption function, your program needs to print the received ciphertext and the
deciphered plaintext.


**Test of Part 2:** Same as Part 1.

**Test of Part 3:** Your program needs to take a manually input plaintext message from the
command line, and print the average time of encryption and decryption for each key size for
AES and RSA.

## III. Other Instructions

Any programming language is fine.

Messages must be manually input from command line (not from a file). Results must be printed
to the command line (not to a file).

Submit your code and necessary support files (including an empty _ctext_ file and, if any, the file(s)
used to exchange shared secret key and public key) to Blackboard as a .zip file named in this
format: HW2.YourLastName.YourFirstName.zip.

