module Main where

import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 9) regF 
    , Jump (Abs 141) 
    , Nop 
    , Load (ImmValue 6) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 95) 
    , Nop 
    , Load (ImmValue 0) regE 
    , Load (ImmValue 6) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Compute Add regB regE regE 
    , Push regE 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 6) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Compute Add regB regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 6) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Compute Add regB regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Load (ImmValue 6) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 0) regB 
    , Compute Add regF regB regB 
    , Compute Add regB regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Pop regB 
    , Pop regA 
    , Compute Mul regA regB regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 6) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 6) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (ImmValue 6) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 12) 
    , Load (ImmValue 3) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue (-7)) regC 
    , Compute Add regF regC regC 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regC regC 
    , Load (IndAddr regA) regD 
    , Store regD (IndAddr regC) 
    , Compute Sub regA regB regA 
    , Compute Sub regC regB regC 
    , Load (ImmValue 1) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regC regC 
    , Load (IndAddr regA) regD 
    , Store regD (IndAddr regC) 
    , Compute Sub regA regB regA 
    , Compute Sub regC regB regC 
    , Load (ImmValue 0) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regC regC 
    , Load (IndAddr regA) regD 
    , Store regD (IndAddr regC) 
    , Compute Sub regA regB regA 
    , Compute Sub regC regB regC 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regB 
    , Jump (Ind regB) 
    , Nop 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 9) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 6) regA 
    , Push regA 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 5) regD 
    , Compute Add regF regD regD 
    , Load (ImmValue 2) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 1) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 0) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 8) regD 
    , Compute Add regF regD regD 
    , Load (ImmValue 2) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 1) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 0) regB 
    , Compute Add regB regA regA 
    , Compute Add regB regD regD 
    , Load (IndAddr regA) regC 
    , Store regC (IndAddr regD) 
    , Compute Sub regA regB regA 
    , Compute Sub regD regB regD 
    , Load (ImmValue 8) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 251) regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-7)) regB 
    , Compute Add regF regB regB 
    , Load (ImmValue 0) regC 
    , Compute Add regB regC regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Compute Sub regB regC regB 
    , Load (ImmValue 1) regC 
    , Compute Add regB regC regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Compute Sub regB regC regB 
    , Load (ImmValue 2) regC 
    , Compute Add regB regC regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Compute Sub regB regC regB 
    , Load (ImmValue (-8)) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 3) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , EndProg 
    ]

main = runWithDebugger (debuggerSimplePrint myShow) [prog0]

