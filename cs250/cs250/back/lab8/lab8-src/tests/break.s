 # Reserve space
	.data
i:
	.long 0
	.long 0

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

	# push 0
	movq $0,%rbx
	#Assign to Global var i
	movq %rbx, i
# FOR_LOOP_START
loop_start_0:
	movq i, %rbx

	# push 15
	movq $15,%r10

	# <
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je loop_end_0
	jmp oneloop_start_0
twoloop_start_0:
loop_continue_0:
	movq i, %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var i
	movq %rbx, i
	jmp loop_start_0
oneloop_start_0:
if_start_1:
	movq i, %rbx

	# push 5
	movq $5,%r10

	# ==
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmove %rdi, %rbx
	cmpq $0, %rbx
	je if_end_1
	jmp loop_end_0
	jmp else_end_2
if_end_1:
else_end_2:
	#top=0

	# push string "i=%d\n" top=0
	movq $string0, %rbx
	movq i, %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	jmp twoloop_start_0
# FOR_LOOP_START
loop_end_0:
	#top=0

	# push string "for i=%d\n" top=0
	movq $string1, %rbx
	movq i, %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf

	# push 0
	movq $0,%rbx
	#Assign to Global var i
	movq %rbx, i
# WHILE_LOOP_START
loop_start_2:
loop_continue_1:
	movq i, %rbx

	# push 15
	movq $15,%r10

	# <
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	je loop_end_1
	#top=0

	# push string "i=%d\n" top=0
	movq $string2, %rbx
	movq i, %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
if_start_3:
	movq i, %rbx

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
	jmp loop_end_1
	jmp else_end_4
if_end_3:
else_end_4:
	movq i, %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var i
	movq %rbx, i
	jmp loop_start_2
# WHILE_LOOP_END
loop_end_1:
	#top=0

	# push string "while i=%d\n" top=0
	movq $string3, %rbx
	movq i, %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf

	# push 0
	movq $0,%rbx
	#Assign to Global var i
	movq %rbx, i
# DO_WHILE_LOOP_START
loop_start_4:
loop_continue_2:
	#top=0

	# push string "i=%d\n" top=0
	movq $string4, %rbx
	movq i, %r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
if_start_5:
	movq i, %rbx

	# push 10
	movq $10,%r10

	# ==
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmove %rdi, %rbx
	cmpq $0, %rbx
	je if_end_5
	jmp loop_end_2
	jmp else_end_6
if_end_5:
else_end_6:
	movq i, %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var i
	movq %rbx, i
	movq i, %rbx

	# push 15
	movq $15,%r10

	# <
	cmpq %r10, %rbx
	movq $0, %r10
	movq $0, %rbx
	movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $1, %rbx
	je loop_start_4
# DO_WHILE_LOOP_END
loop_end_2:
	#top=1

	# push string "do/while i=%d\n" top=1
	movq $string5, %r10
	movq i, %r13
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r13, %rsi
	movq %r10, %rdi
	movl    $0, %eax
	call printf
	#top=1

	# push string "OK\n" top=1
	movq $string6, %r10
     # func=printf nargs=1
     # Move values from reg stack to reg args
	movq %r10, %rdi
	movl    $0, %eax
	call printf
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
string0:
	.string "i=%d\n"

string1:
	.string "for i=%d\n"

string2:
	.string "i=%d\n"

string3:
	.string "while i=%d\n"

string4:
	.string "i=%d\n"

string5:
	.string "do/while i=%d\n"

string6:
	.string "OK\n"

