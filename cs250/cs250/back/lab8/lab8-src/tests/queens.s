 # Reserve space
	.data
queens:
	.long 0
	.long 0

 # Reserve space
	.data
solid:
	.long 0
	.long 0

	.text
.globl abs
abs:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
if_start_0:
	#Push Local var v
	movq 128(%rsp), %rbx

	# push 0
	movq $0,%r10

	# <
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je if_end_0

	# push -1
	movq $-1,%rbx
	#Push Local var v
	movq 128(%rsp), %r10

	# *
	imulq %r10,%rbx
	movq %rbx, %rax
	jmp else_end_1
if_end_0:
else_end_1:
	#Push Local var v
	movq 128(%rsp), %rbx
	movq %rbx, %rax
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl check
check:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
# FOR_LOOP_START
loop_start_1:
	#Push Local var i
	movq 120(%rsp), %rbx
	#Push Local var depth
	movq 128(%rsp), %r10

	# <
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je loop_end_0
	jmp oneloop_start_1
twoloop_start_1:
loop_continue_0:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp loop_start_1
oneloop_start_1:
if_start_2:
	#Push Local var i
	movq 120(%rsp), %rbx
	#Push Local var depth
	movq 128(%rsp), %r10

	# ==
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmove %rdi, %rbx
	#Push Local var i
	movq 120(%rsp), %r10
	#Push Local var depth
	movq 128(%rsp), %r13

	# -
	subq %r13, %r10
     # func=abs nargs=1
     # Move values from reg stack to reg args
	movq %r10, %rdi
	call abs
	movq %rax, %r10
	#Push Local var depth
	movq 128(%rsp), %r13
	#Push Local var i
	movq 120(%rsp), %r14

	# -
	subq %r14, %r13

	# ==
	cmpq %r13, %r10
	movq $0, %r13
	movq $0, %r10
	movq $1, %rdi
	cmove %rdi, %r10

	# ||
	addq %r10, %rbx
	cmpq $0, %rbx
	movq $1, %rax
	cmovne %rax, %rbx
	cmpq $0, %rbx
	je if_end_2

	# push 0
	movq $0,%rbx
	movq %rbx, %rax
	jmp else_end_3
if_end_2:
else_end_3:
	jmp twoloop_start_1
# FOR_LOOP_START
loop_end_0:

	# push 1
	movq $1,%rbx
	movq %rbx, %rax
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl bruteforce
bruteforce:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments
	movq %rdi,128(%rsp)
if_start_3:
	#Push Local var depth
	movq 128(%rsp), %rbx

	# push 8
	movq $8,%r10

	# ==
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmove %rdi, %rbx
	cmpq $0, %rbx
	je if_end_3
	#top=0

	# push string "Solution #%2ld = [ " top=0
	movq $string0, %rbx
	movq solid, %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq solid, %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var solid
	movq %rbx, solid

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
# FOR_LOOP_START
loop_start_4:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 8
	movq $8,%r10

	# <
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je loop_end_1
	jmp oneloop_start_4
twoloop_start_4:
loop_continue_1:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp loop_start_4
oneloop_start_4:
	#top=0

	# push string "%ld " top=0
	movq $string1, %rbx
	#Push Local var i
	movq 120(%rsp), %r10

	# push 1
	movq $1,%r13

	# +
	addq %r13,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	jmp twoloop_start_4
# FOR_LOOP_START
loop_end_1:
	#top=0

	# push string "]\n" top=0
	movq $string2, %rbx
     # func=printf nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	movl    $0, %eax
	call printf

	# push 0
	movq $0,%rbx
	movq %rbx, %rax
	jmp else_end_5
if_end_3:
else_end_5:

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
# FOR_LOOP_START
loop_start_5:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 8
	movq $8,%r10

	# <
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je loop_end_2
	jmp oneloop_start_5
twoloop_start_5:
loop_continue_2:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp loop_start_5
oneloop_start_5:
	#Push Local var depth
	movq 128(%rsp), %rbx
	#Push Local var i
	movq 120(%rsp), %r10
if_start_6:
	#Push Local var depth
	movq 128(%rsp), %r13
     # func=check nargs=1
     # Move values from reg stack to reg args
	movq %r13, %rdi
	call check
	movq %rax, %r13

	# push 0
	movq $0,%r14

	# !=
	cmpq %r14, %r13
	movq $0, %r14
	movq $0, %r13
	movq $1, %rdi
	cmovne %rdi, %r13
	cmpq $0, %r13
	je if_end_6
	#Push Local var depth
	movq 128(%rsp), %r13

	# push 1
	movq $1,%r14

	# +
	addq %r14,%r13
     # func=bruteforce nargs=1
     # Move values from reg stack to reg args
	movq %r13, %rdi
	call bruteforce
	movq %rax, %r13
	jmp else_end_7
if_end_6:
else_end_7:
	jmp twoloop_start_5
# FOR_LOOP_START
loop_end_2:

	# push 0
	movq $0,%r14
	movq %rbx, %rax
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
	.text
.globl main
main:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments

	# push 8
	movq $8,%rbx

	# push 8
	movq $8,%r10

	# *
	imulq %r10,%rbx
     # func=malloc nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call malloc
	movq %rax, %rbx
	#Assign to Global var queens
	movq %rbx, queens

	# push 1
	movq $1,%rbx
	#Assign to Global var solid
	movq %rbx, solid

	# push 0
	movq $0,%rbx
     # func=bruteforce nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call bruteforce
	movq %rax, %rbx
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
string0:
	.string "Solution #%2ld = [ "

string1:
	.string "%ld "

string2:
	.string "]\n"

