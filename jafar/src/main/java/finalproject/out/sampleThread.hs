module Main where

import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue (-1)) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (ImmValue 67) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 89) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Nop 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 1) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 4) 
    , Nop 
    , ReadInstr (DirAddr 7) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 22) 
    , Nop 
    , ReadInstr (DirAddr 10) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 28) 
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
    , Nop 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 2) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 3) 
    , Nop 
    , ReadInstr (DirAddr 8) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 11) 
    , Nop 
    , ReadInstr (DirAddr 9) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 17) 
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
    , TestAndSet (DirAddr 13) 
    , Receive regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 6) 
    , Load (ImmValue 17) regA 
    , Push regA 
    , ReadInstr (DirAddr 17) 
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
    , Load (ImmValue 18) regA 
    , Push regA 
    , ReadInstr (DirAddr 18) 
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
    , WriteInstr reg0 (DirAddr 13) 
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
    , TestAndSet (DirAddr 13) 
    , Receive regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 6) 
    , Load (ImmValue 17) regA 
    , Push regA 
    , ReadInstr (DirAddr 17) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 12) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , ReadInstr (DirAddr 18) 
    , Receive regA 
    , Push regA 
    , Load (ImmValue 16) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , WriteInstr reg0 (DirAddr 13) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 9) 
    , EndProg 
    ]

prog4 :: [Instruction]
prog4 = [
    Nop 
    , ReadInstr (DirAddr 4) 
    , Receive regA 
    , Load (ImmValue 1) regB 
    , Compute Xor regA regB regA 
    , Branch regA (Abs 0) 
    , Load (ImmValue 19) regA 
    , Push regA 
    , Load (ImmValue 19) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , WriteInstr regA (IndAddr regD) 
    , Load (ImmValue 1) regA 
    , WriteInstr regA (DirAddr 10) 
    , EndProg 
    ]

main = runWithDebugger (debuggerSimplePrint myShow) [prog0,prog1,prog2,prog3,prog4]

