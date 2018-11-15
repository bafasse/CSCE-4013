**CSCE 4013/5013 Applied Cryptography – Homework 5**

**Due date: November 11, 2018**

**1. (20 points)**  Suppose an aggregator wants to know the sum of _n_ users&#39; ages. Design a scheme which allows the aggregator to obtain the sum without revealing any individual user&#39;s age to any other party. Assumptions about the system: users can communicate to each other; no party is trusted; at most two users can collude; no user colludes with the aggregator; no party can eavesdrop the communications between any two users. You must design your own solution. Do not copy from any publication.

**One way to go about getting the sum of all**  **n**  **users ages is to have the users give the aggregator their age plus some random number**  **x**** , which the aggregator won&#39;t know. Once the aggregator has the sum, **** S ****, ask the users to subtract their random number from**  **S**  **and once you&#39;ve reached the last user you will have the sum of all user&#39;s ages without having known what that was.**

**2. (16 pts)** Suppose Merkle hash tree is used to authenticate eight messages, m1, m2, …, m8. The eight messages are used to build the Merkle tree, and the root of the tree is signed with a digital signature s.

i) To authenticate m3, besides m3 itself and the signature s of the tree root, which elements should be included in the message? How can the receiver verify the integrity of m3?

**In order to authenticate m3 in this manner you would need the hash value of m4 since its parent is the hash of the hash of m3 and the hash of m4, H(H(m3)+H(m4))**

ii) To authenticate m8, besides m8 itself and the signature s of the tree root, which elements should be included in the message? How can the receiver verify the integrity of m8?

**In order to authenticate m8 in this manner you would need the hash value of m7 since its parent is the hash of the hash of m7 and the hash of m8**

**H(H(m7)+H(m8))**







**3. (12 pts)** Suppose user A is broadcasting packets to n recipients B1, …, Bn. Privacy is not important but integrity is. In other words, each of B1, …, Bn should be assured that the packets he is receiving were sent by A. User A decides to use a MAC.

a. Suppose user A and B1, …, Bn all share a secret key k. User A MACs every packet she sends using k. Each user B­i can then verify the MAC. Explain why this scheme is insecure, namely, show that user B1 is not assured that packets he is receiving are from A.

**Since A and all**  **B**** i **** share the same secret key, **** k ****, then any**  **B**** i **** can send messages to anyone in the group that shares **** k ****. Because of this, B**** 1 **** can never be sure the message he&#39;s receiving came from A**

b. Suppose user A has a set _S_ = {_k __1_, …, _k__ m_} of _m_ secret keys. Each user _B __i_ has some subset ­_S__ i_ of the keys. When A transmits a packet she appends m MACs to it by MACing the packet with each of her _m_ keys. When user _B __i_ receives a packet he accepts it as valid only if all MAC&#39;s corresponding to keys in _S__ i_ are valid. What property should the sets _S __1_, …, _S__ n_ satisfy so that the attack from part (a) does not apply? We are assuming all users _B __1_, …, _B__ n_ are sufficiently far apart so that they cannot collude.

**User B**** i **** can fool **** B ****j**** , assuming **** i **** ≠ **** j ****, only if**  **B**** i **** has every key **** B ****j**  **has. This can be seen when user**  **B**** j **** verifies a message by verifying the MACs corresponding to the keys he has. One way to fix this is for every user **** B ****i**** , Bj **** to have a separate secret key that is not shared.**

c. Show that when _n_ = 10 (i.e. ten recipients) the broadcaster A need only append 5 MAC&#39;s to every packet to satisfy the condition of part (b). Describe the subsets _S __1_, …, _S__ 10_ of set {_k __1_, …, _k__ 5_} you would use.

**Note that** 10=C52 **, which means every**  **S**** i **** only has 2 keys.**

**S**** 1 **** : { ****k**** 1 ****,k**** 2 ****}**

**S**** 2 **** : { ****k**** 1 ****,k**** 3 ****}**

**S**** 3 **** : { ****k**** 1 ****,k**** 4 ****}**

**S**** 4 **** : { ****k**** 1 ****,k**** 5 ****}**

**S**** 5 **** : { ****k**** 2 ****,k**** 3 ****}**

**S**** 6 **** : { ****k**** 2 ****,k**** 4 ****}**

**S**** 7 **** : { ****k**** 2 ****,k**** 5 ****}**

**S**** 8 **** : { ****k**** 3 ****,k**** 4 ****}**

**S**** 9 **** : { ****k**** 3 ****,k**** 5 ****}**

**S**** 10 **** : { ****k**** 4 ****,k**** 5 ****}**





**4. (12 pts)** In the HORS one-time signature (refer to the slide deck of Module 9 for details), let _t_=16, _l_=128, _k_=4, and let H() be a hash function that creates 16-bit long hash values. Suppose a message m&#39;s hash value is 0110 1001 0011 0010. Then what is this message&#39;s signature? How to verify the signature?

**Start out by generating sixteen 128-bit strings which will form private key**  **SK**  **= {**** s ****1**** , …, s ****16**** }. The public key will be computed as **** PK **** = { ****v**** 1 ****, …, v**** 16 ****}. Let**  **h**  **=**  **H**** ( ****m**** ) and split **** h **** into **** k **** = 4 substrings of **log216** bits each, with each **** h ****j**  **being interpreted as an integer**  **i**** j ****:**

**h**** 1 **** = 0110 ****2**  **= 6**** 10**

**h**** 2 **** = 1001 ****2**  **= 9**** 10**

**h**** 3 **** = 0011 ****2**  **= 3**** 10**

**h**** 4 **** = 0010 ****2**  **= 2**** 10**

**The signature will be {s**** 6 ****,**  **s**** 9 ****, s**** 3­ ****, s**** 2 ****}**

**In order to verify a signature** {s1&#39;,s2&#39;,s3&#39;,s4&#39;} **over message**  **m**** , compute **** h ****=**  **H**** ( ****m**** ) and split **** h **** into **** k **** = 4 substrings **** h ****1**** , h ****2**** , h ****3**** , h ****4**  **of** log216 **bits each. Interpret each**  **h**** j **** as an integer **** i ****j**  **and check if** f(sj&#39;)=vij **:**

f(s6&#39;)=v6

f(s9&#39;)=v9

f(s3&#39;)=v3

f(s2&#39;)=v2

**5. (20 pts)** Revise the Onion Routing protocol presented in the slides of Module 11 to have one new protocol that meets two requirements:

(i) there are only two relays N1 and N2;

**Alice to N1**

**E**** e1 ****{k1,N1**** ,E ****e2**** [k2,N2,D|m]}**

**N1 to N2**

**E**** e2****[k2,D|m]**

**N2 uses its private key to decrypt the final layer and sends message to destination D**

(ii) the source Alice doesn&#39;t want N2 know the content of the request m. Give the request sending and data reply process.