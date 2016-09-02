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

	# push 20
	movq $20,%r10

	# *
	imulq %r10,%rbx
     # func=malloc nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	call malloc
	movq %rax, %rbx
	#Assign to Local var a
	movq %rbx, 128(%rsp)

	# push 0
	movq $0,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
# FOR_LOOP_START
loop_start_0:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 20
	movq $20,%r10

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
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Local var i
	movq %rbx, 120(%rsp)
	jmp loop_start_0
oneloop_start_0:
	#Push Local var i
	movq 120(%rsp), %rbx

	# push 3
	movq $3,%r10
	#Push Local var i
	movq 120(%rsp), %r13

	# *
	imulq %r13,%r10
	jmp twoloop_start_0
# FOR_LOOP_START
loop_end_0:
	#top=2

	# push string "Ok so far\n" top=2
	movq $string0, %r13
     # func=printf nargs=1
     # Move values from reg stack to reg args
	movq %r13, %rdi
	movl    $0, %eax
	call printf

	# push 0
	movq $0,%r13
	#Assign to Local var i
	movq %r13, 120(%rsp)
# FOR_LOOP_START
loop_start_1:
	#Push Local var i
	movq 120(%rsp), %r13

	# push 20
	movq $20,%r14

	# <
	cmpq %r14, %r13
	movq $0, %r14
	movq $0, %r13
	movq $1, %rdi
	cmovl %rdi, %r13
	cmpq $0, %r13
	je loop_end_1
	jmp oneloop_start_1
twoloop_start_1:
loop_continue_1:
	#Push Local var i
	movq 120(%rsp), %r13

	# push 1
	movq $1,%r14

	# +
	addq %r14,%r13
	#Assign to Local var i
	movq %r13, 120(%rsp)
	jmp loop_start_1
oneloop_start_1:
	#top=0

	# push string "%d: %d\n" top=0
	movq $string1, %rbx
	#Push Local var i
	movq 120(%rsp), %r10
	#Push Local var i
	movq 120(%rsp), %r13
     # func=printf nargs=3
     # Move values from reg stack to reg args
	movq %r13, %rdx
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	jmp twoloop_start_1
# FOR_LOOP_START
loop_end_1:
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
string0:
	.string "Ok so far\n"

string1:
	.string "%d: %d\n"

