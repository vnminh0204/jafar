import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue (-1)) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 11) regA 
    , Push regA 
    , Load (ImmValue 35) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , WriteInstr regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , WriteInstr regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , WriteInstr regA (IndAddr regB) 
    , Pop regD 
    , Nop 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 1) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 2) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 3) 
    , Nop 
    , ReadInstr (DirAddr 7) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 34) 
    , Nop 
    , ReadInstr (DirAddr 8) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 40) 
    , Nop 
    , ReadInstr (DirAddr 9) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 46) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Pop regA 
    , ReadInstr (IndAddr regA) 
    , Receive regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , ReadInstr (IndAddr regA) 
    , Receive regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , ReadInstr (IndAddr regA) 
    , Receive regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , EndProg 
    ]

prog1 :: [Instruction]
prog1 = [
    Nop 
    , ReadInstr (DirAddr 1) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 21) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Jump (Abs 83) 
    , Nop 
    , TestAndSet (DirAddr 12) 
    , Receive regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 15) 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 99) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 99) 
    , Pop regE 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 18) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 99) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 99) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 18) regA 
    , Compute Add regA regD regD 
    , ReadInstr (IndAddr regD) 
    , Receive regD 
    , Push regD 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 21) regA 
    , Push regA 
    , ReadInstr (DirAddr 21) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , WriteInstr reg0 (DirAddr 12) 
    , Nop 
    , ReadInstr (DirAddr 21) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 14) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 7) 
    , EndProg 
    ]

prog2 :: [Instruction]
prog2 = [
    Nop 
    , ReadInstr (DirAddr 2) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 22) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Jump (Abs 83) 
    , Nop 
    , TestAndSet (DirAddr 12) 
    , Receive regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 15) 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 99) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 99) 
    , Pop regE 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 18) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 99) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 99) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 18) regA 
    , Compute Add regA regD regD 
    , ReadInstr (IndAddr regD) 
    , Receive regD 
    , Push regD 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 22) regA 
    , Push regA 
    , ReadInstr (DirAddr 22) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , WriteInstr reg0 (DirAddr 12) 
    , Nop 
    , ReadInstr (DirAddr 22) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 14) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 8) 
    , EndProg 
    ]

prog3 :: [Instruction]
prog3 = [
    Nop 
    , ReadInstr (DirAddr 3) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 23) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Jump (Abs 83) 
    , Nop 
    , TestAndSet (DirAddr 12) 
    , Receive regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 15) 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 99) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 99) 
    , Pop regE 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 18) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 0) regD 
    , Push regD 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 99) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 99) 
    , Pop regD 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Push regD 
    , Pop regD 
    , Load (ImmValue 18) regA 
    , Compute Add regA regD regD 
    , ReadInstr (IndAddr regD) 
    , Receive regD 
    , Push regD 
    , Load (ImmValue 20) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 23) regA 
    , Push regA 
    , ReadInstr (DirAddr 23) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , WriteInstr reg0 (DirAddr 12) 
    , Nop 
    , ReadInstr (DirAddr 23) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 14) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 9) 
    , EndProg 
    ]

main = run [prog0,prog1,prog2,prog3]

